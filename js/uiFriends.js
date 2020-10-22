document.querySelector('#btnSaveFriend').addEventListener('click', saveFriend);
drawFriendsTable();

function saveFriend(){
	var sId = document.querySelector('#txtId').value
	var sName = document.querySelector('#name').value
	var sLastName = document.querySelector('#lastName').value
	var sEmail = document.querySelector('#email').value

	addFriend(sId,sName,sLastName,sEmail);
	drawFriendsTable();
}

function drawFriendsTable(){
	var list = getFriendList();
		tbody = document.querySelector('#friendsTable tbody');

	tbody.innerHTML = '';

	for(var i=0; i<list.length; i++){
		var row = tbody.insertRow(i),
			idCell = row.insertCell(0),
			nameCell = row.insertCell(1),
			lastNameCell = row.insertCell(2),
			emailCell = row.insertCell(3),
			selectCell = row.insertCell(4);

		idCell.innerHTML = list[i].id;
		nameCell.innerHTML = list[i].name;
		lastNameCell.innerHTML = list[i].lastName;
		emailCell.innerHTML = list[i].email;

		var inputSelect = document.createElement('input');
		inputSelect.type= 'radio';
		inputSelect.value = list[i].id;
		selectCell.appendChild(inputSelect);

		tbody.appendChild(row);
	}
}