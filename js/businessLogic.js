var friendList = [];

function addFriend(pid, pname, plastName, pemail){
	var newFriend = {
		id: pid,
		name: pname,
		lastName: plastName,
		email: pemail
	};
	friendList.push(newFriend);
	localStorageFriendList(friendList);
}

function getFriendList(){
	var storedList = localStorage.getItem('localFriendList');
	if ( storedList == null){
		friendList = [];
	}else{
		friendList = JSON.parse(storedList);
	}
	return friendList;
}

function localStorageFriendList(plist){
	localStorage.setItem('localFriendList', JSON.stringify(plist));
}