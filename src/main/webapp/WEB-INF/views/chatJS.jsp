<%--
  Created by IntelliJ IDEA.
  User: Danil
  Date: 5/21/2018
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    // loads group details
    function selectGroup(groupId) {
        $.ajax({
            type : 'GET',
            url : '${pageContext.request.contextPath}/jchat/groups/' + groupId,
            success : function (data) {
                $('.messages-menu').html(data);
                setGroupName();
                autoScroll();
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

    $(document).ready(function () {
        // sets active condition to group item
        $(this).on('click', '.groups-item', function() {
            $(this).parent().find('li').removeClass('group-active');
            $(this).toggleClass('group-active');
        });

        $(this).on('click', '.groups-item', function () {
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
        $(this).on('click', '.send-message-btn', function () {
            stompClient.send('/app/rooms/' + currentGroupId, {}, $('#send-form').val());
            $('#send-form').val('');
        });

        // group edit name button event
        $(this).on('click', '.group-edit-btn', function () {
            var newGroupName = $('.group-edit-input').val();
            $.ajax({
                type : 'POST',
                url : '${pageContext.request.contextPath}/jchat/group-edit',
                data : { groupId : currentGroupId,  groupName : newGroupName },
                success : function () {
                    $('.groups-item[group-item-id=' + currentGroupId + '] .group-name span').text(newGroupName);
                    $('.group-details-name span').text(newGroupName);
                }
            });
        });

        // message click event
        $(this).on('click', '.message', function () {
            var currMessage = $(this);
            var currMsgControl  = currMessage.find('.message-control');
            currMsgControl.toggleClass('d-none');
            currMsgControl.toggleClass('d-flex');

            // message delete event
            currMessage.on('click', '.message-delete', function () {
                $.ajax({
                    type : 'POST',
                    url : '${pageContext.request.contextPath}/jchat/delete-mssage',
                    data : { msgId : currMessage.attr('message-item-id') },
                    success : function () {

                    }
                });
            });
        });
    });

    // connect to specific group
    function connectToGroup(obj) {
        currentGroupId = $(obj).attr('group-item-id');
        stompClient.subscribe('/topic/group/' + currentGroupId, function (message) {
            getMessage(JSON.parse(message.body));
        });
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
</script>
