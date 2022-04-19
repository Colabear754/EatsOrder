function writeSave(){
	
	if(document.writeform.title.value==""){
	  alert("제목을 입력해주세요.");
	  document.writeform.title.focus();
	  return false;
	}
	if(document.writeform.writer.value==""){
	  alert("작성자를 입력해주세요.");
	  document.writeform.writer.focus();
	  return false;
	}
	if(document.writeform.content.value==""){
	  alert("내용을 입력해주세요.");
	  document.writeform.content.focus();
	  return false;
	}
	if(document.writeform.filename.value==""){
	  alert("파일을 첨부해주세요.");
	  document.writeform.filename.focus();
	  return false;
	}
 }    
