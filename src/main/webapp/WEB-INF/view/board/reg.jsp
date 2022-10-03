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
    <form id="form_submit" action="/test/ajax" method="post" enctype="multipart/form-data">
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="name" name="name" placeholder="이름">
            <label for="name">이름</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="age" name="age" placeholder="나이">
            <label for="age">나이</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="phone" name="phone" placeholder="전화번호">
            <label for="phone">전화번호</label>
        </div>
        <div class="form-floating mb-3">
        <select class="form-select" id="gender" name="gender" aria-label="Default select example">
            <option selected>성별</option>
            <option value="man">남자</option>
            <option value="woman">여자</option>
        </select>
        </div>
        <div class="form-floating mb-3" id="fileDiv">
            <input type="button" id="fileAdd" name="fileAdd" value="추가">
            <div id="fileBox">
<%--                <input class="form-control"  name="files" type="file" id="file1">--%>
            </div>
        </div>
        <input type="button" id="Btn_Submit" value="전송"/>
    </form>

    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="testVal" name="testVal" placeholder="테스트용">
        <label for="testVal">제이쿼리 값 가져오기</label>
        <input type="button" onclick="getJquery()" value="실행">
    </div>




<script type="text/javascript">

    function getJquery() {
        // 제이쿼리로 name이 testVal인 값 가젹오기
        var value = $('input[name=testVal]').val();
        console.log(value);
        alert(value);
    }

    // 첨부파일 개수
    var cnt = 2;

    // 첨부 파일 개수 추가
    $("#fileAdd").click(function () {

        if (cnt > 3) {
            alert("첨부는 최대 3개요");
            return;
        }

        var str= '<div class="mb-3"> ' +
            '<label for="formFile" class="form-label">파일 선택</label> ' +
            '<input class="form-control"  name="files" type="file" id="file' + cnt + '"> ' +
            '<input type="button" name="delete" id="delete" onclick="delete_file(this.id)" value="삭제">' +
            '</div>';
        $("#fileBox").append(str);
        cnt++;
    });

    // 첨부 파일 삭제
    function delete_file(id) {
       var getId = document.getElementById(id);
       getId.parentElement.remove();
       cnt--;
    }

    // 전송
    $("#Btn_Submit").click(function () {

        var form = $('#form_submit')[0];    // 이렇게 하면 id가 form_submit인 Form에 있는 모든 데이터를 가져온다.
        var formData = new FormData(form);

        $.ajax({
            type: "POST",
            enctype: "multipart/form-data",
            url: "/test/ajax",
            data: formData,
            contentType: false,
            processData: false,
            success: function (res) {
                console.log(res);
                alert("문자열, 데이터 전송 성공~~");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("실패ㅠㅠ");
            }
        })
    })
</script>

</body>
</html>