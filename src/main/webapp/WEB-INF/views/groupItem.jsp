<%--
  Created by IntelliJ IDEA.
  User: 4567
  Date: 06.05.2018
  Time: 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Group item -->
<li group-item-id="${pageContext.request.getParameter("groupId")}" class="list-group-item groups-item row d-flex justify-content-around align-items-center">
    <div class="col-3">
        <img class="img-fluid" src="../../resources/img/avatar-inside-a-circle.svg" alt="avatar" width="45" height="45">
    </div>
    <div class="col-4">
        <div class="group-name d-flex justify-content-start"><span class="d-inline-block text-truncate" style="max-width: 80px;">${pageContext.request.getParameter("groupName")}</span></div>
        <div class="group-preview d-flex justify-content-start"><span class="d-inline-block text-truncate" style="max-width: 80px;">Hello! How are you?</span></div>
    </div>
    <div class="col-4 d-flex justify-content-center">
        <span class="d-inline-block text-truncate" style="max-width: 60px;">${pageContext.request.getParameter("groupDate")}</span>
    </div>
    <div class="col-1 d-flex justify-content-center">
        <span class="badge badge-success badge-pill">14</span>
    </div>
</li>
