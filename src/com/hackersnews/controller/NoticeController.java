package com.hackersnews.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.hackersnews.dao.NoticeDao;
import com.hackersnews.model.Notice;
import com.hackersnews.model.User;

public class NoticeController {

	public static Notice searchNotice(int id) {
		try {
			return NoticeDao.getNoticeById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static boolean createNotice(User user, String title, String url) {
		try {
			Notice notice = new Notice(user, title, url, new Date());
			NoticeDao.save(notice);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public static boolean deleteNotice(Notice notice) {
		try {
			NoticeDao.delete(notice.getId());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public static boolean editNotice(Notice notice, String title) {
		try {
			notice.setTitle(title);
			NoticeDao.update(notice);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public static ArrayList<Notice> getNoticesNewest() {
		int numberNotices = 19;
		ArrayList<Notice> noticesNewest = null;
			noticesNewest = NoticeDao.findAllNotices();
		
		noticesNewest = quickSortDate(noticesNewest);
		if (noticesNewest.size() > numberNotices) {
			for (int i = numberNotices; i < noticesNewest.size(); i++) {
				noticesNewest.remove(i);
			}
		}
		return (ArrayList<Notice>) noticesNewest;
	}

	public static ArrayList<Notice> getNoticesRanking() throws SQLException {
		ArrayList<Notice> noticesRanking = NoticeDao.findAllNotices();
		
		noticesRanking = quickSortPoints(noticesRanking);
		try {
			noticesRanking = (ArrayList<Notice>) noticesRanking.subList(0, 19);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return noticesRanking;
	}

	private static ArrayList<Notice> quickSortDate(ArrayList<Notice> array) {
		ArrayList<Notice> array1 = new ArrayList<Notice>();
		ArrayList<Notice> less_subarray = new ArrayList<Notice>();
		ArrayList<Notice> greater_subarray = new ArrayList<Notice>();

		if (array.size() == 0) {
			// el arreglo esta ordenado
			return array;
		} else {
			// tomar el primer elemento como pivote
			Notice pivot = array.get(0);
			for (int i = 1; i < array.size(); i++) {
				if (array.get(i).getCreatedAt().after(pivot.getCreatedAt())) {
					less_subarray.add(array.get(i));
				} else {
					greater_subarray.add(array.get(i));
				}
			}

			less_subarray = quickSortDate(less_subarray);
			greater_subarray = quickSortDate(greater_subarray);

			if (less_subarray.size() < 1 && greater_subarray.size() < 1) {
				array1.add(pivot);
			} else {
				if (less_subarray.size() < 1) {
					array1.add(pivot);
					for (int i = 0; i < greater_subarray.size(); i++) {
						array1.add(greater_subarray.get(i));
					}
				} else {
					if (greater_subarray.size() < 1) {
						for (int i = 0; i < less_subarray.size(); i++) {
							array1.add(less_subarray.get(i));
						}
						array1.add(pivot);
					} else {
						for (int i = 0; i < less_subarray.size(); i++) {
							array1.add(less_subarray.get(i));
						}
						array1.add(pivot);
						for (int i = 0; i < greater_subarray.size(); i++) {
							array1.add(greater_subarray.get(i));
						}
					}
				}
			}

		}
		return array1;
	}

	private static ArrayList<Notice> quickSortPoints(ArrayList<Notice> array) {
		ArrayList<Notice> array1 = new ArrayList<Notice>();
		ArrayList<Notice> less_subarray = new ArrayList<Notice>();
		ArrayList<Notice> greater_subarray = new ArrayList<Notice>();

		if (array.size() == 0) {
			// el arreglo esta ordenado
			return array;
		} else {
			// tomar el primer elemento como pivote
			Notice pivot = array.get(0);
			for (int i = 1; i < array.size(); i++) {
				if (NoticeDao.findPointsByNotice(array.get(i)) > NoticeDao.findPointsByNotice(pivot)) {
					less_subarray.add(array.get(i));
				} else {
					greater_subarray.add(array.get(i));
				}
			}

			less_subarray = quickSortPoints(less_subarray);
			greater_subarray = quickSortPoints(greater_subarray);

			if (less_subarray.size() < 1 && greater_subarray.size() < 1) {
				array1.add(pivot);
			} else {
				if (less_subarray.size() < 1) {
					array1.add(pivot);
					for (int i = 0; i < greater_subarray.size(); i++) {
						array1.add(greater_subarray.get(i));
					}
				} else {
					if (greater_subarray.size() < 1) {
						for (int i = 0; i < less_subarray.size(); i++) {
							array1.add(less_subarray.get(i));
						}
						array1.add(pivot);
					} else {
						for (int i = 0; i < less_subarray.size(); i++) {
							array1.add(less_subarray.get(i));
						}
						array1.add(pivot);
						for (int i = 0; i < greater_subarray.size(); i++) {
							array1.add(greater_subarray.get(i));
						}
					}
				}
			}

		}
		return array1;
	}

	private static ArrayList<Notice> quickSortId(ArrayList<Notice> array) {
		ArrayList<Notice> array1 = new ArrayList<Notice>();
		ArrayList<Notice> less_subarray = new ArrayList<Notice>();
		ArrayList<Notice> greater_subarray = new ArrayList<Notice>();

		if (array.size() == 0) {
			// el arreglo esta ordenado
			return array;
		} else {
			// tomar el primer elemento como pivote
			Notice pivot = array.get(0);
			for (int i = 1; i < array.size(); i++) {
				if (array.get(i).getId() < pivot.getId()) {
					less_subarray.add(array.get(i));
				} else {
					greater_subarray.add(array.get(i));
				}
			}

			less_subarray = quickSortId(less_subarray);
			greater_subarray = quickSortId(greater_subarray);

			if (less_subarray.size() < 1 && greater_subarray.size() < 1) {
				array1.add(pivot);
			} else {
				if (less_subarray.size() < 1) {
					array1.add(pivot);
					for (int i = 0; i < greater_subarray.size(); i++) {
						array1.add(greater_subarray.get(i));
					}
				} else {
					if (greater_subarray.size() < 1) {
						for (int i = 0; i < less_subarray.size(); i++) {
							array1.add(less_subarray.get(i));
						}
						array1.add(pivot);
					} else {
						for (int i = 0; i < less_subarray.size(); i++) {
							array1.add(less_subarray.get(i));
						}
						array1.add(pivot);
						for (int i = 0; i < greater_subarray.size(); i++) {
							array1.add(greater_subarray.get(i));
						}
					}
				}
			}

		}
		return array1;
	}

	private static Notice binarySearch(ArrayList<Notice> notices, int id) {
		int n = notices.size();
		int centro, inf = 0, sup = n - 1;
		while (inf <= sup) {
			centro = (sup + inf) / 2;
			if (notices.get(centro).getId() == id)
				return notices.get(centro);
			else if (id < notices.get(centro).getId()) {
				sup = centro - 1;
			} else {
				inf = centro + 1;
			}
		}
		return null;
	}

}