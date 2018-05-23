<%--
  Created by IntelliJ IDEA.
  User: 4567
  Date: 30.04.2018
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Message -->
<div message-item-id="${pageContext.request.getParameter("msgId")}" class="row message align-items-start justify-content-center">
    <div class="col-2 sender-avatar d-flex align-items-center justify-content-center">
        <img class="img-fluid" src="../../resources/img/avatar-inside-a-circle.svg" alt="avatar" height="55" width="55">
    </div>
    <div class="col-10 message-body">
        <div class="message-info">
            <span class="sender-name">${pageContext.request.getParameter("msgSender")}</span>
            <span class="send-date">${pageContext.request.getParameter("msgDate")}</span>
        </div>
        <div class="message-payload">${pageContext.request.getParameter("msgBody")}</div>
    </div>
    <div class="col-12 message-control d-none justify-content-end align-items-center">
        <button class="message-delete btn btn-secondary">Delete</button>
        <button class="message-edit btn btn-secondary">Edit</button>
    </div>
    <div class="col-12 message-edit-menu d-none">
        <textarea name="edit-field" id="message-edit-field"></textarea>
        <button class="message-save-edit btn btn-secondary">Save message</button>
    </div>
</div>
