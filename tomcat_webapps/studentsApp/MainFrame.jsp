<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Список студентов</title>
    </head>

    <body>
        <form action="<c:url value="/main"/>" method="POST">
            <table>
                <tr>
                    <td>Год:<input type="text" name="year" value="${form.year}"/><br/></td>
                    <td>Список групп:
                        <select name="groupId">
                            <c:forEach var="group" items="${form.groups}">
                                <c:choose>
                                    <c:when test="${group.groupId==form.groupId}">
                                        <option value="${group.groupId}" selected><c:out value="${group.nameGroup}"/></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${group.groupId}"><c:out value="${group.nameGroup}"/></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="submit" name="getList" value="Обновить"/></td>
                </tr>
            </table>

            <p/>
            <b>Список студентов для выбранных параметров:<b>
            <br/>
            <table>
                <tr>
                    <th> </th>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Отчество</th>
                </tr>
                <c:forEach var="student" items="${form.students}">
                <tr>
                    <td><input type="radio" name="studentId" value="${student.studentId}"></td>
                    <td><c:out value="${student.surName}"/></td>
                    <td><c:out value="${student.firstName}"/></td>
                    <td><c:out value="${student.patronymic}"/></td>
                </tr>
                </c:forEach>
            </table>

            <table>
                <tr>
                    <td><input type="submit" value="Add" name="Add"/></td>
                    <td><input type="submit" value="Edit" name="Edit"/></td>
                    <td><input type="submit" value="Delete" name="Delete"/></td>
                </tr>
            </table>

            <p/>
            <b>Переместить студентов в группу<b>
            <br/>
            <table>
                <tr>
                   <td>Год:<input type="text" name="newYear" value="${form.year}"/><br/></td>
                    <td>Список групп:
                        <select name="newGroupId">
                        <c:forEach var="group" items="${form.groups}">
                            <option value="${group.groupId}"><c:out value="${group.nameGroup}"/></option>
                        </c:forEach>
                        </select>
                    </td>
                    <td><input type="submit" name="MoveGroup" value="Переместить"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>