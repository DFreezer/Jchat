<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 4567
  Date: 30.04.2018
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700,900" rel="stylesheet">
    <link href="../../resources/css/hamburgers.css" rel="stylesheet">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/main.css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js" integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+" crossorigin="anonymous"></script>
    <script type="text/javascript" src="<s:url value="/webjars/sockjs-client/1.1.2/sockjs.min.js"/>"></script>
    <script type="text/javascript" src="<s:url value="/webjars/stomp-websocket/2.3.3-1/stomp.min.js"/>"></script>
    <%--<script type="text/javascript" src="<s:url value="/webjars/jquery/3.3.1-1/jquery.min.js"/>"></script>--%>
    <title>JChat</title>
    <script>
        var socket = new SockJS('/group');
        var stompClient = Stomp.over(socket);
        stompClient.connect({}, function () {
            setTimeout(hideLoader, 1500);
        });
        var currentGroupId;
        var isConnected = false;
    </script>
</head>
<body>
<div class="container-fluid main-container h-100 d-flex flex-column">
    <!-- Header -->
    <nav class="navbar navbar-expand-sm navbar-dark">
        <button class="hamburger hamburger--slider" type="button">
				<span class="hamburger-box">
					<span class="hamburger-inner"></span>
				</span>
        </button>
        <a class="navbar-brand logo" href="#">
            <img src="../../resources/img/chat-bubbles-with-ellipsis.svg" width="25" height="25" class="d-inline-block align-top" alt="">
            <span class="logo-title">JChat</span>
        </a>
    </nav>
    <!-- Content -->
    <div class="row h-100 content-container">
        <!-- Groups menu -->
        <div class="col-5 col-sm-4 col-md-4 col-lg-3 col-xl-2 h-100 group-menu">
            <div class="group-items">
                <!-- groups list -->
                <ul class="list-group user-groups">
                    <c:forEach var="group" items="${groupList}" >
                        <!-- Group item -->
                        <li group-item-id="${group.idGroup}" class="list-group-item groups-item row d-flex justify-content-around align-items-center">
                            <div class="col-3">
                                <img class="img-fluid" src="../../resources/img/avatar-inside-a-circle.svg" alt="avatar" width="45" height="45">
                            </div>
                            <div class="col-4">
                                <div class="group-name d-flex justify-content-start"><span class="d-inline-block text-truncate" style="max-width: 80px;"><c:out value="${group.name}"/></span></div>
                                <div class="group-preview d-flex justify-content-start"><span class="d-inline-block text-truncate" style="max-width: 80px;">Hello! How are you?</span></div>
                            </div>
                            <div class="col-4 d-flex justify-content-center">
                                <span class="d-inline-block text-truncate" style="max-width: 60px;"><c:out value="${group.regDate.toLocalDate()}"/></span>
                            </div>
                            <div class="col-1 d-flex justify-content-center">
                                <span class="badge badge-success badge-pill">14</span>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <!-- Left slider menu -->
            <div class="left-slider-menu h-100">
                <div class="row m-0 account-info justify-content-center">
                    <div class="col-4 account-avatar">
                        <img class="img-fluid" src="../../resources/img/avatar-inside-a-circle.svg" alt="" height="80" width="80">
                    </div>
                    <div class="col-8 p-0 account-name">
                        ${username}
                    </div>
                </div>
                <div class="row m-0 chat-menu">
                    <div class="col-12 p-0">
                        <div class="row m-0 create-group" data-toggle="modal" data-target="#modal-create-group">
                            <div class="col-3 d-flex justify-content-center align-items-center"><i class="fas fa-users"></i></div>
                            <div class="col-9 p-0 title">Create group</div>
                        </div>
                        <div class="row m-0 contacts" data-toggle="modal" data-target="#modal-contacts">
                            <div class="col-3 d-flex justify-content-center align-items-center"><i class="far fa-address-book"></i></div>
                            <div class="col-9 p-0 title">Contacts</div>
                        </div>
                        <div class="row m-0 settings">
                            <div class="col-3 d-flex justify-content-center align-items-center"><i class="fas fa-cog"></i></div>
                            <div class="col-9 p-0 title">Settings</div>
                        </div>
                        <div class="row m-0 logout">
                            <div class="col-3 d-flex justify-content-center align-items-center">
                                <i class="fas fa-sign-out-alt"></i>
                            </div>
                            <div class="col-9 p-0 title">Log out</div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal create group menu -->
            <div class="modal fade modal-create-group" id="modal-create-group" tabindex="-1" role="dialog" aria-labelledby="modal-title" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modal-title">Create group</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-group">
                                        <label for="group-name">Group name</label>
                                        <input type="text" class="form-control" id="group-name" placeholder="Enter group name">
                                    </div>
                                </div>
                            </div>
                            <div class="row m-0">
                                <div class="col-12 p-0">
                                    <div class="input-group mb-3 user-list-search">
                                        <input type="text" class="form-control group-user-search" placeholder="Search users" aria-label="Search" aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-secondary" type="button">Search</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 p-0" style="height: 350px; overflow-x: hidden; overflow-y: auto;">
                                    <label for="user-list">Choose users</label>
                                    <ul class="list-group" id="user-list">
                                        <c:forEach items="${userContacts}" var="contacts">
                                            <li class="list-group-item list-group-item-action user-list-item">
                                                <div class="row">
                                                    <div class="col-1 d-flex justify-content-center pr-0 user-avatar">
                                                        <img src="../../resources/img/avatar-inside-a-circle.svg" alt="avatar" height="20" width="20">
                                                    </div>
                                                    <div class="col-11 user-info">
                                                        <div class="user-name">${contacts.contact.username}</div>
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
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-dark" onclick="createGroup($('#group-name').val())">Create</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal contacts menu -->
            <div class="modal fade modal-contacts" id="modal-contacts" tabindex="-1" role="dialog" aria-labelledby="modal-contacts-title" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modal-contacts-title">Create group</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="row m-0">
                                <div class="col-12 p-0">
                                    <div class="input-group mb-3 contacts-list-search">
                                        <input type="text" class="form-control" placeholder="Search users" aria-label="Search" aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-secondary" type="button">Search</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 p-0" style="height: 350px; overflow-x: hidden; overflow-y: auto;">
                                    <label for="contacts-list">Contacts:</label>
                                    <ul class="list-group" id="contacts-list">
                                        <c:forEach items="${userContacts}" var="contacts">
                                            <li class="list-group-item list-group-item-action contact-list-item">
                                                <div class="row">
                                                    <div class="col-1 d-flex justify-content-center pr-0 user-avatar">
                                                        <img src="../../resources/img/avatar-inside-a-circle.svg" alt="avatar" height="20" width="20">
                                                    </div>
                                                    <div class="col-11 user-info">
                                                        <div class="user-name">${contacts.contact.username}</div>
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
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-dark" onclick="createGroup($('#group-name').val())">Create</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Messages menu -->
        <div class="col-7 col-sm-8 col-md-8 col-lg-9 col-xl-10 d-flex h-100 flex-column messages-menu">

        </div>
    </div>
</div>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" integrity="sha256-T0Vest3yCU7pafRw9r+settMBX6JkKN06dqBnpQ8d30=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<%--<jsp:include page="chatJS.jsp"/>--%>
<script type="text/javascript">
    // loads group details
    function selectGroup(groupId) {
        $.ajax({
            type : 'GET',
            url : '${pageContext.request.contextPath}/jchat/groups/' + groupId,
            success : function (data) {
                $('.messages-menu').html(data);
                setGroupName();
                autoScroll();
            },
            error : function (data) {
                alert(data.responseText);
            }
        });
    }

    // adds message to
    function getMessage(message) {
        $.ajax({
            type : 'GET',
            url : '${pageContext.request.contextPath}/jchat/message',
            data : { msgId : message.messageId, msgSender : message.sender, msgBody : message.body, msgDate : message.date },
            success : function (data) {
                $('.messages').append(data);
                autoScroll();
            },
            error : function (data) {
                $('body').html(data.responseText);
            }
        });
    }

    function createGroup(groupName) {
        $.ajax({
            type : 'POST',
            url : '${pageContext.request.contextPath}/jchat/create-group',
            data : {groupName : groupName},
            success : function (group) {
                $.ajax({
                    type : 'GET',
                    url : '${pageContext.request.contextPath}/jchat/group-item',
                    data : {groupId : group.idGroup, groupName : group.name, groupDate : group.regDate},
                    success : function (data) {
                        $('.user-groups').append(data);
                        $('#modal-create-group').modal('toggle');
                    },
                    error : function (data) {
                        $('body').html(data.responseText);
                    }
                });
            }
        });
    }

    // scrolls messages box to the bottom
    function autoScroll() {
        $('.messages').animate({
            scrollTop: $('.messages').get(0).scrollHeight
        }, 10);
    }

    // sets group name to group details
    function setGroupName() {
        $('.group-details-name span').text($('.list-group .group-active .group-name span').text());
    }

    // sets active condition to group item
    $('body').on('click', '.groups-item', function() {
        $(this).parent().find('li').removeClass('group-active');
        $(this).toggleClass('group-active');
    });

    $('body').on('click', '.groups-item', function () {
        if(!isConnected) {
            connectToGroup(this);
        } else {
            if(currentGroupId != null) {
                stompClient.unsubscribe(currentGroupId);
                isConnected = false;
                connectToGroup(this);
            }
        }
    });

    // message send button event
    $('body').on('click', '.send-message-btn', function () {
        stompClient.send('/app/rooms/' + currentGroupId, {}, $('#send-form').val());
        $('#send-form').val('');
    });

    // group edit name button event
    $('body').on('click', '.group-edit-btn', function () {
        var newGroupName = $('.group-edit-input').val();
        $.ajax({
            type : 'POST',
            url : '${pageContext.request.contextPath}/jchat/group-edit',
            data : { groupId : currentGroupId,  groupName : newGroupName },
            success : function () {
                $('.groups-item[group-item-id=' + currentGroupId + '] .group-name span').text(newGroupName);
                $('.group-details-name span').text(newGroupName);
            },
            error : function (data) {
                $('body').html(data.responseText);
            }
        });
    });

    // message click event
    $('body').on('click', '.message', function (e) {
        e.stopPropagation();
        var currMessage = $(this);
        var currMsgControl  = currMessage.find('.message-control');
        currMsgControl.toggleClass('d-none');
        currMsgControl.toggleClass('d-flex');

        // message delete event
        currMessage.on('click', '.message-delete', function () {
            var msgId = currMessage.attr('message-item-id');
            $.ajax({
                type : 'POST',
                url : '${pageContext.request.contextPath}/jchat/delete-message',
                data : { msgId : msgId },
                success : function () {
                    currMessage.remove();
                },
                error : function (data) {
                    $('body').html(data.responseText);
                }
            });
        });
        var currMsgEdit = currMessage.find(".message-edit-menu");
        // message edit event
        currMessage.on('click', '.message-edit', function (e) {
            e.stopPropagation();
            currMsgEdit.toggleClass('d-none');
            currMsgEdit.toggleClass('d-block');
            currMsgEdit.find('#message-edit-field').val(currMessage.find('.message-payload').text());
            currMessage.on('click', '.message-save-edit', function (e) {
                e.stopPropagation();
                var newPayload = currMsgEdit.find('#message-edit-field').val();
                console.log(newPayload);
                $.ajax({
                    type : 'POST',
                    url : '${pageContext.request.contextPath}/jchat/edit-message',
                    data : { msgId : currMessage.attr('message-item-id'), msgBody : newPayload },
                    success : function () {
                        currMessage.find('.message-payload').text(newPayload);
                        currMsgEdit.toggleClass('d-none');
                        currMsgEdit.toggleClass('d-block');
                    },
                    error : function (data) {
                        $('body').html(data.responseText);
                    }
                });
            });
        });
    });

    $('body').on('click', '.search-btn', function () {
        $.ajax({
            type : 'POST',
            url : '${pageContext.request.contextPath}/jchat/search-messages',
            data : { username : $('#search-form').val(), idGroup : currentGroupId },
            success : function (messages) {
                $('.messages').empty();
                $.each(messages, function (key, message) {
                    $.ajax({
                        type : 'GET',
                        url : '${pageContext.request.contextPath}/jchat/message',
                        data : { msgId : message.message.idMessage, msgSender : message.message.sender.username, msgBody : message.message.body, msgDate : new Date(message.message.date).toLocaleDateString() },
                        success : function (data) {
                            $('.messages').append(data);
                            autoScroll();
                        }
                    });
                });
            },
            error : function (data) {
                $('body').html(data.responseText);
            }
        });
    });

    // connect to specific group
    function connectToGroup(obj) {
        currentGroupId = $(obj).attr('group-item-id');
        stompClient.subscribe('/topic/group/' + currentGroupId, function (message) {
            getMessage(JSON.parse(message.body));
        }, { id : currentGroupId });
        isConnected = true;
        selectGroup(currentGroupId);
    }

    // hides loader
    function hideLoader() {
        $('.loader-wrapper').removeClass('d-flex');
        $('.loader-wrapper').removeClass('justify-content-center');
        $('.loader-wrapper').removeClass('align-items-center');
        $('.loader-wrapper').css({"display" : "none"});
    }

    $('.left-slider-menu').hide();

    // hamburger menu click event
    $('.hamburger').click(function() {
        if ($(this).hasClass('is-active')) {
            $(this).removeClass('is-active');
            $('.left-slider-menu').toggle();
            $('.left-slider-menu').css({"left" : "-100%"});
        } else {
            $(this).addClass('is-active');
            $('.left-slider-menu').toggle();
            $('.left-slider-menu').css({"left" : "0"});
        }
    });

    // create group search autocomplete
    $('.group-user-search').autocomplete({
        source: function( request, response ) {
            $.ajax( {
                url: '${pageContext.request.contextPath}/jchat/search-contacts',
                data: {
                    term : request.term
                },
                success: function(data) {
                    response(
                        $.map(data, function(value) {
                            return { label : value.contact.username, value : value.contact.idUser }
                        })
                    );
                }
            });
        },
        minLength: 0,
        response : function(event, ui) {
            $('.user-list-item').each(function(key) {
                var currItem = $(this);
                $.each(ui.content, function (key, value) {
                    if(value.label !== currItem.find('.user-name').text()) {
                        currItem.hide();
                    } else {
                        currItem.show();
                        return false;
                    }
                });
            });
        }
    });

    // contact search item event
    $('body').on('click', '.user-list-item', function () {
        $(this).toggleClass('active');
    });
</script>
<!-- Loader -->
<div class="loader-wrapper d-flex justify-content-center align-items-center">
    <div class="loader"></div>
</div>
</body>
</html>
