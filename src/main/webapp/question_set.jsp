<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("#cr1").click(function(){
  	$("#correcttag").val($("#o1").val())
  });
  
  $("#cr2").click(function(){
  	$("#correcttag").val($("#o2").val())
  });
  
  $("#cr3").click(function(){
  	$("#correcttag").val($("#o3").val())
  });
  
  $("#cr4").click(function(){
  	$("#correcttag").val($("#o4").val())
  });
  
 
});
</script>
</head>
<body>
	<form action="question_insert.jsp" method="post">
	
	Question:  <input type="text" name="question" /><br/><br/>
	Option1:  <input type="text" id="o1" name="option1" />  <input type="radio" id="cr1" name="cradio" value="o1" required/><br/>
	Option2:  <input type="text" id="o2" name="option2" />  <input type="radio" id="cr2" name="cradio" value="o2"/><br/>
	Option3:  <input type="text" id="o3" name="option3" />  <input type="radio" id="cr3" name="cradio" value="o3"/><br/>
	Option4:  <input type="text" id="o4" name="option4" />  <input type="radio" id="cr4" name="cradio" value="o4"/><br/>
	Correct Answer:  <input type="text" id="correcttag" name="correct" readonly/><br/>
	<br/>
	<input type="submit"/>
	</form>
	
	<br/><br/>
	<a href="index.jsp">Home</a>
</body>
</html>