package net.narusas.cafelibrary.bookfactories;

import java.util.Collections;
import java.util.List;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookComparator;

public class FetchResult {
	int totalCount;
	int currentPage;
	List<Book> res;
	String query;
	private int resultCount = 19;

	public FetchResult(List<Book> res, String query) {
		Collections.sort(res, new BookComparator(BookComparator.SORT_COL.COL_TITLE, false));
		this.res = res;
		this.query = query;
	}

	public boolean isLastPageResult() {
		return currentPage >= getTotalPage();
	}

	public boolean isFirstPageResult() {
		return currentPage == 1;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Book> getRes() {
		return res;
	}

	public String getQuery() {
		return query;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setRes(List<Book> res) {
		this.res = res;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public int getTotalPage() {
		if (resultCount == 0) {
			return 1;
		}

		return getTotalCount() / resultCount + (getTotalCount() % resultCount != 0 ? 1 : 0);
	}

	public int getResultCount() {
		return resultCount;
	}

}
