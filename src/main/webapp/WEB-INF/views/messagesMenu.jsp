<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 4567
  Date: 30.04.2018
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Group details -->
<div class="row group-details d-flex justify-content-end align-items-center">
    <div class="col-6">
        <div class="group-details-name"><span></span></div>
        <div class="group-details-members" data-toggle="modal" data-target="#exampleModal">
            <img class="img-fluid" src="../../resources/img/avatar.svg" alt="member-icon" width="14" height="14">
            <span>${usersCount}</span>
        </div>
        <!-- Modal user list-->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Members</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" style="height: 350px; overflow-x: hidden; overflow-y: auto;">
                        <div class="row">
                            <div class="col-12">
                                <ul class="list-group members-list">
                                    <c:forEach items="${groupUsers}" var="usersList">
                                        <li class="list-group-item list-group-item-action members-list-item">
                                            <div class="row">
                                                <div class="col-1 d-flex justify-content-center pr-0 user-avatar">
                                                    <img src="../../resources/img/avatar-inside-a-circle.svg" alt="avatar" height="20" width="20">
                                                </div>
                                                <div class="col-11 user-info">
                                                    <div class="user-name">${usersList.user.username}</div>
                                                    <div class="user-status">Online</div>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-6 d-flex justify-content-end align-items-center">
        <!-- Group settings -->
        <div class="group-settings">
            <div class="dropdown">
                <button class="btn dropdown-toggl settings-button" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <img src="../../resources/img/settings.svg" alt="settings" height="25" width="25">
                    <span class="caret"></span>
                </button>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#modal-edit-group">Edit group name</a>
                    <a class="dropdown-item" href="#">Mute</a>
                    <a class="dropdown-item" href="#">Leave group</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Messages Search -->
<div class="row messages-search align-items-center">
    <div class="col-12">
        <div class="input-group">
            <input type="text" id="search-form" class="form-control" placeholder="Enter sender name" aria-label="Enter sender name" aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary search-btn" type="button">
                    Send
                </button>
            </div>
        </div>
    </div>
</div>
<!-- Modal edit group name -->
<div class="modal fade modal-edit-group" id="modal-edit-group" tabindex="-1" role="dialog" aria-labelledby="modal-title" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modal-title">Edit group name</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label for="group-edit-name">New group name</label>
                            <input type="text" class="form-control group-edit-input" id="group-edit-name" placeholder="Enter new group name">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-dark group-edit-btn">Edit</button>
            </div>
        </div>
    </div>
</div>
<!-- Messages menu -->
<div class="row messages-menu">
    <div class="col-12 messages-menu-col">
        <!-- Messages history -->
        <div class="row messages-history align-items-end">
            <div class="col-12 mh-100 mt-auto messages">
                <c:forEach items="${groupMessages}" var="messages">
                    <!-- Message -->
                    <div message-item-id="${messages.idGroupMessage}" class="row message align-items-start justify-content-center">
                        <div class="col-2 sender-avatar d-flex align-items-center justify-content-center">
                            <img class="img-fluid" src="../../resources/img/avatar-inside-a-circle.svg" alt="avatar" height="55" width="55">
                        </div>
                        <div class="col-10 message-body">
                            <div class="message-info">
                                <span class="sender-name">${messages.message.sender.username}</span>
                                <span class="send-date">${messages.message.date.toLocalDate()}</span>
                            </div>
                            <div class="message-payload">${messages.message.body}</div>
                        </div>
                        <div class="col-12 message-control d-none justify-content-end align-items-center">
                            <button class="message-delete btn btn-secondary">Delete</button>
                            <button class="message-edit btn btn-secondary">Edit</button>
                        </div>
                        <div class="col-12 message-edit-menu d-none">
                            <textarea name="message-edit-field" id="message-edit-field"></textarea>
                            <button class="message-save-edit btn btn-secondary">Save message</button>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- Messages send -->
        <div class="row messages-send align-items-center">
            <div class="col-12">
                <div class="input-group">
                    <input type="text" id="send-form" class="form-control" placeholder="Enter message" aria-label="Enter message" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button">
                            <i class="far fa-smile"></i>
                        </button>
                        <button class="btn btn-outline-secondary send-message-btn" type="button">
                            Send
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
