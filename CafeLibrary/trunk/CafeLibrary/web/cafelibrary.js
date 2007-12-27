// JavaScript Document

function findBook(bookDatas, id) {
	for(i=0; i<bookDatas.length;i++) {
		book = bookDatas[i];
		if (book.id == id) {
			return book;
		}		
	}
	return null;
	
}

function updateBookList(bookListData){
	updateList(bookListData,'bookLists'); 
	
}

function updateList(srcList, target) {
	d = document.getElementById(target);	
	t = "";
	for(i=0;i<srcList.length;i++) {
		bookList = srcList[i];
		t += "<li><a href=\"javascript:bookListSelected("+bookList.id+")\">"+bookList.name+"("+bookList.books.length+")</a></li>\n";
	}
	d.innerHTML = t;
}
function updateBorrowerList(borrowersData){
	updateList(borrowersData,'borrowers');
}

function bookListSelected(id) {
	bookListSelectedLoop(id, bookListData);
	bookListSelectedLoop(id, borrowersData);
}

function bookListSelectedLoop(id, srcList) {
	for(i=0;i<srcList.length;i++) {
		bookList = srcList[i];
		if (bookList.id == id) {
			selectBookList(bookList);
			break;
		}
	}
}

function selectBookList(bookList) {
	d = document.getElementById('BookTable');
	t= "<table border='1' cellpadding='0' cellspacing='0' width='99%' class='BookTableTable'>"
				+"<tr class='TableHeader'>"
				+"	<td class='TableHeaderTitle'>"+titleHeader+"</td>"
				+"	<td class='TableHeaderTitle'>"+authorHeader+"</td>"
				+"	<td class='TableHeaderTitle'>"+favoriteHeader+"</td>"
				+"</tr>";	
	for(i=0;i<bookList.books.length;i++) {
		book = bookList.books[i];
		t+="<tr>";
		t+= "<td class=\"BookTitleInTable\"><a href=\"javascript:viewBook("+book.id+")\">"+book.title+"</a></td>";
		t+= "<td class=\"BookAuthorInTable\">"+book.author+"</td>";
		t+= "<td class=\"BookFavoriteInTable\"><img src=\"img/favorite_star_"+book.favorite+".png\"></td>";
		t+="</tr>";
	}
	t+="</table>";
	d.innerHTML = t;
	
}

function viewBook(id) {
	book = findBook(bookDatas, id);
	
	document.getElementById('bookTitle').innerHTML = book.title;
	document.getElementById('bookAuthor').innerHTML = book.author;
	document.getElementById('publisher').innerHTML = book.publisher;
	document.getElementById('publisherDate').innerHTML = book.publishDate;
	document.getElementById('category').innerHTML = book.category;
	document.getElementById('isbn').innerHTML = book.isbn;
	document.getElementById('translator').innerHTML = book.translator;
	document.getElementById('purchaseDate').innerHTML = book.purchaseDate;
	document.getElementById('favorite').src = "img/favorite_star_"+book.favorite+".png";
	document.getElementById('borrower').innerHTML = book.borrower;
	document.getElementById('description').innerHTML = book.description;
	document.getElementById('note').innerHTML = book.notes;	
	document.getElementById('coverImg').src = book.cover;
}

function cleanUp() {
	document.getElementById('bookTitle').innerHTML = "";
	document.getElementById('bookAuthor').innerHTML = "";
	document.getElementById('publisher').innerHTML = "";
	document.getElementById('publisherDate').innerHTML = "";
	document.getElementById('category').innerHTML = "";
	document.getElementById('isbn').innerHTML = "";
	document.getElementById('translator').innerHTML = "";
	document.getElementById('purchaseDate').innerHTML = "";
	document.getElementById('favorite').src = "img/favorite_star_0.png";
	document.getElementById('borrower').innerHTML = "";
	document.getElementById('description').innerHTML = "";
	document.getElementById('note').innerHTML = "";	
	document.getElementById('coverImg').src = "";
}