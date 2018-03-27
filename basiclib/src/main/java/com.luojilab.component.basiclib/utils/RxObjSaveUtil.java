package com.luojilab.component.basiclib.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import static com.luojilab.component.basiclib.utils.RxTool.getContext;


/**
 * 保存缓存类
 *
 * @author CD
 *
 */
public class RxObjSaveUtil {

	public static File location = getContext().getFilesDir();

	/**
	 * 保存对象
	 *
	 * @param ser
	 * @param //location
	 * @return
	 */
	// ser是你继承了Base的类
	public static boolean saveObject(List<? extends Serializable> ser,
									 String locationName) {

		String path = location + locationName;

		FileOutputStream fos = null;

		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream(new File(path));
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ser);
			oos.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				oos.close();
			} catch (Exception e) {
			}
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
	}


	/**
	 * 读取对象
	 *
	 * @param //file
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static List<? extends Serializable> readObject(String locationName) {
		String path = location + locationName;

		if (!isExistDataCache(path))
			return null; // 判断缓存是否存在
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(new File(path));
			ois = new ObjectInputStream(fis);
			return (List<? extends Serializable>) ois.readObject();
		} catch (FileNotFoundException e) {
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof InvalidClassException) {
				File data = new File(path);
				data.delete();

			}
		} finally {
			try {
				ois.close();
			} catch (Exception e) {
			}
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return null;
	}


	/**
	 * 判断请求数据缓存
	 *
	 * @param cachefile
	 * @return
	 */
	public static boolean isExistDataCache(String cachefile) {
		boolean exist = false;
		File data = new File(cachefile);
		if (data.exists())
			exist = true;
		return exist;
	}

	/**
	 * 删除缓存
	 *
	 * @param locationName
	 *            文件名字
	 */

	public static void deleteDataCache(String locationName) {

		String path = location + locationName;

		File data = new File(path);

		delete(data);
	}

	static void delete(File file) {
		if (file.isFile()) {
			file.delete();
			return;
		}
		if (file.isDirectory()) {
			File[] childFiles = file.listFiles();
			if (childFiles == null || childFiles.length == 0) {
				file.delete();
				return;
			}
			for (int i = 0; i < childFiles.length; i++) {
				delete(childFiles[i]);
			}
			file.delete();
		}
	}

	/**
	 * 删除文件
	 *
	 * @param locationName
	 *            文件名字
	 */

	public static void deleteDataFile(String locationName) {

		File data = new File(locationName);

		delete(data);
	}
}
