<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>연습장</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <!--    부트스트랩   -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</head>
<body>
<table class="table">

    <button type="button" class="btn btn-success" onclick="fn_Blind()">블라인드</button>

    <thead>
    <tr>
        <th scope="col"><input class="form-check-input" onclick="fn_Blind_ALL(this)" type="checkbox" id="flexCheckDefault"></th>
        <th scope="col">번호</th>
        <th scope="col">이름</th>
        <th scope="col">성별</th>
        <th scope="col">전화번호</th>
        <th scope="col">블라인드 여부</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${boards}" var="board">
            <tr>
                <td><input class="form-check-input" type="checkbox" name="blindYn" value="${board.boardId}"></td>
                <td>${board.boardId}</td>
                <td>${board.name}</td>
                <td>${board.gender}</td>
                <td>${board.phone}</td>
                <td>${board.blindYn}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="/save">등록하기</a>
<jsp:include page="/WEB-INF/view/common/pagination.jsp"/>

<script type="text/javascript">

    function fn_Blind() {

        var ids = document.getElementsByName("blindYn").length;

        for (var i = 0; i < ids; i++) {
            if (document.getElementsByName("blindYn")[i].checked == true) {
                console.log(document.getElementsByName("blindYn")[i].value);
            }
        }

        let arr = [];

        // 선택된 boardId값들을 배열로 넣기
        for (let i = 0; i < ids; i++) {
            if (document.getElementsByName("blindYn")[i].checked == true) {
                let val = document.getElementsByName("blindYn")[i].value;
                console.log(val);
                arr.push(val);
            }
        };

        let formData = {
            "data": arr
        };

        $.ajax({
            type: "POST",
            url: "/board/blind",
            contentType: "application/json",    // ajax->java
            dataType: 'json',   // java -> ajax(success or error)
            data: JSON.stringify(formData),
            success: function (res) {
                console.log(res);
                alert("블라인드 처리 했다요");
            },
            error: function (err) {
                alert("실패ㅠㅠ");
            }
        })
    }

    function fn_Blind_ALL(selectAll) {

        const checkboxes = document.querySelectorAll('input[type="checkbox"]');

        // forEach로 반복문 돌리기.
        checkboxes.forEach((checkbox) => {
            // board의 체크박스 각각이 체크되는 것이 selectAll이라는 거 하나가 체크되는 것과 같다.
            checkbox.checked = selectAll.checked;
        })
    }


</script>

</body>
</html>