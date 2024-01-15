var currentUser = null;
var isLoggedIn = false;
var meetingToEdit;
var campaignToEdit;
var adventurerToEdit;

window.addEventListener('DOMContentLoaded', function(evt) {
	init();
});

function init() {
	let regDivShowBtn = document.getElementById("registerBtn");
	regDivShowBtn.addEventListener("click", showRegistration);
	let loginDivShowBtn = document.getElementById("loginBtn");
	loginDivShowBtn.addEventListener("click", showLogin);
	let regDivShowBtnNav = document.getElementById("registerBtnNav");
	regDivShowBtnNav.addEventListener("click", showRegistration);
	let loginDivShowBtnNav = document.getElementById("loginBtnNav");
	loginDivShowBtnNav.addEventListener("click", showLogin);
	
	let addPlayerBtn = document.getElementById('addNewPlayer');
	
	
	addPlayerBtn.addEventListener('click', function(e) {
		e.preventDefault();
		addPlayer();
	});
	
	loadGameInfo();


	if (currentUser === null) {
		isLoggedIn = false;
	} else if (currentUser !== null) {
		isLoggedIn = true;
	}

	updateNavbar(isLoggedIn);
	updatePage(isLoggedIn);


	console.log(currentUser)

	let loginButton = document.getElementById('userAuth');
	loginButton.addEventListener('click', function(e) {
		e.preventDefault();
		loginPlayer();
	});

}

function updateNavbar(isLoggedIn) {
	let accountBtnNav = document.getElementById("accountBtnNav");
	let registerBtnNav = document.getElementById("registerBtnNav");
	let loginBtnNav = document.getElementById("loginBtnNav");

	if (isLoggedIn) {
		accountBtnNav.style.display = 'block';
		accountBtnNav.addEventListener('click', function() {
			updatePage(isLoggedIn);
		});
		registerBtnNav.style.display = 'none';
		loginBtnNav.textContent = 'Logout';
	} else {
		accountBtnNav.style.display = 'none';
		registerBtnNav.style.display = 'block';
		loginBtnNav.style.display = 'block';
	}
}

function updatePage(isLoggedIn) {
	let gameInfoDiv = document.getElementById("gameInfo");
	let playerDataDiv = document.getElementById("playerData");
	let regDiv = document.getElementById("registerDiv");
	let loginDiv = document.getElementById("loginDiv");
	let welcomeDiv = document.getElementById("welcome");
	let editCampDiv = document.getElementById("editCampaign");
	let editMeetDiv = document.getElementById("editMeeting");
	let editAdventDiv = document.getElementById("editAdventurer");
	
	editCampDiv.style.display = 'none';
	editMeetDiv.style.display = 'none';
	editAdventDiv.style.display = 'none';
	regDiv.style.display = 'none';
	loginDiv.style.display = 'none';

	if (isLoggedIn) {
		gameInfoDiv.style.display = 'none';
		playerDataDiv.style.display = 'block';
		welcomeDiv.style.display = 'none';
		loadCampaigns();
		loadAdventurers();
		loadMeetings();
	} else {
		welcomeDiv.style.display = 'block';
		gameInfoDiv.style.display = 'block';
		playerDataDiv.style.display = 'none';
	}

}

function showRegistration() {
	let regDiv = document.getElementById("registerDiv");
	let gameInfoDiv = document.getElementById("gameInfo");
	gameInfoDiv.style.display = 'none';
	regDiv.style.display = 'block';
}

function showLogin() {
	let loginBtnNav = document.getElementById("loginBtnNav");
	if (loginBtnNav.textContent === 'Logout') {
		currentUser = null;
		isLoggedIn = false;
		loginBtnNav.textContent = 'Login';
		updateNavbar(isLoggedIn);
		updatePage(isLoggedIn);
	}
	let loginDiv = document.getElementById("loginDiv");
	let gameInfoDiv = document.getElementById("gameInfo");
	gameInfoDiv.style.display = 'none';
	loginDiv.style.display = 'block';
}


function loadGameInfo() {
	let xhr = new XMLHttpRequest;
	xhr.open('GET', 'api/games');

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				let gameInfo = JSON.parse(xhr.responseText);
				displayGameInfo(gameInfo);
			} else {
				displayError("There is an issue connecting to the server");
			}
		}
	};

	xhr.send();

}

function displayGameInfo(games) {
	let parentDiv = document.getElementById("gameInfo");

	games.forEach(function(game) {
		let gameRow = document.createElement('div');
		gameRow.className = "row mb-4 rounded p-3"; 

		let gameHeader = document.createElement("h2");
		let gameIcon = document.createElement('img');
		let gameDescription = document.createElement('p'); 

		gameHeader.textContent = game.name;
		gameIcon.src = game.imageURL;
		gameIcon.className = "img-thumbnail rounded"; 
		gameDescription.textContent = game.description;
		gameRow.appendChild(gameHeader);

		let imageColumn = document.createElement('div');
		imageColumn.className = "col-md-4";
		imageColumn.appendChild(gameIcon);

		gameRow.appendChild(imageColumn);

		let descriptionColumn = document.createElement('div');
		descriptionColumn.className = "col-md-8 d-flex align-items-center justify-content-center"; // Center text vertically and horizontally
		descriptionColumn.appendChild(gameDescription);

		gameRow.appendChild(descriptionColumn);

		parentDiv.appendChild(gameRow);
	});
}

function loginPlayer() {
	let username = login.username.value;
	let xhr = new XMLHttpRequest;
	xhr.open('GET', 'api/players/username/' + username);
	console.log(username);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				let player = JSON.parse(xhr.responseText);
				authenticatePlayer(player);
			} else {
				displayError("No Player Found");
			}
		}
	};

	xhr.send();
}

function authenticatePlayer(player) {
	let password = login.password.value;
	console.log(password);
	console.log(player);
	if (password == player.password) {
		currentUser = player;
		isLoggedIn = true;
		updateNavbar(isLoggedIn);
		updatePage(isLoggedIn);
	} else {
		console.log("Incorrect password")
	}
}

function addPlayer() {
		
		let player = {
			firstName: document.getElementById('firstNameReg').value,
			lastName: document.getElementById('lastNameReg').value,
			address: parseInt(document.getElementById('address').value),
			username: document.getElementById('username').value,
			password: parseInt(document.getElementById('password').value)
			};
			
		let xhr = new XMLHttpRequest();
		xhr.open('POST', 'api/players');
		xhr.setRequestHeader('Content-type', 'application/JSON');

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status === 201) {
					alert("Player has been added")
				} else {
					console.log('error');
				}
			}
		};
		xhr.send(JSON.stringify(player));	
	}

function loadMeetings() {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/meetings');

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				let meetingList = JSON.parse(xhr.responseText);
				meetingList = meetingList.filter(meeting => {
					let isCurrentUserInvolved = false;

					if (meeting.campaign.storyteller.id === currentUser.id) {
						isCurrentUserInvolved = true;
					}

					let players = meeting.campaign.players;
					console.log(players);
					players.forEach(player => {
						if (player.id === currentUser.id) {
							isCurrentUserInvolved = true;
						}
					});

					return isCurrentUserInvolved;
				});
				displayMeetingList(meetingList);
			} else {
				displayError("No Meetings Found");
			}
		}
	};

	xhr.send();
}

function displayMeetingList(meetings) {
	let meetingTableBody = document.getElementById('meetingTableBody');
	meetingTableBody.innerHTML = '';

	meetings.forEach(meeting => {
		let row = meetingTableBody.insertRow();

		let locationCell = row.insertCell(0);
		locationCell.textContent = meeting.location;

		let startTimeCell = row.insertCell(1);
		startTimeCell.textContent = meeting.startTime;

		let campaignCell = row.insertCell(2);
		campaignCell.textContent = meeting.campaign.name;

		let actionCell = row.insertCell(3);
		let editButton = document.createElement('button');
		editButton.textContent = 'Edit';
		editButton.addEventListener('click', () => editMeeting(meeting.id));

		let deleteButton = document.createElement('button');
		deleteButton.textContent = 'Delete';
		deleteButton.addEventListener('click', () => deleteMeeting(meeting.id));

		actionCell.appendChild(editButton);
		actionCell.appendChild(deleteButton);
	});
}


function deleteMeeting(meetingId) {
	let confirmed = confirm('Are you sure you want to delete this campaign?');
	if (confirmed) {
		let xhr = new XMLHttpRequest;

		xhr.open('DELETE', 'api/meetings/' + meetingId);

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status === 204) {
					let confirmDelete = xhr.responseText;
					if (confirmDelete === true) {
						alert("Meeting deleted successfully!")
					};
				} else if (xhr.status === 404) {
					displayError("Meetings Not Found");
				} else {
					displayError("There was an Error deleting this meeting.")
				}
			}
		};

		xhr.send();
	}
	loadMeetings();
}

function loadAdventurers() {
	let xhr = new XMLHttpRequest;

	xhr.open('GET', 'api/players/' + currentUser.id + '/adventurers');

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				let adventurers = JSON.parse(xhr.responseText);
				console.log(adventurers);
				displayAdventurers(adventurers);
			} else {
				displayError("No Meetings Found");
			}
		}
	};

	xhr.send();
}

function displayAdventurers(adventurers) {
	let adventurerTableBody = document.getElementById('adventurerTableBody');
	adventurerTableBody.innerHTML = '';

	adventurers.forEach(adventurer => {
		let row = adventurerTableBody.insertRow();

		let nameCell = row.insertCell(0);
		nameCell.textContent = adventurer.name;

		let classCell = row.insertCell(1);
		classCell.textContent = adventurer.class != null ? adventurer.class : "Not Applicable";

		let levelCell = row.insertCell(2);
		levelCell.textContent = adventurer.level != null ? adventurer.level : "Not Applicable";

		let playerCell = row.insertCell(3);
		playerCell.textContent = adventurer.player.firstName + " " + adventurer.player.lastName;

		let campaignCell = row.insertCell(4);
		campaignCell.textContent = adventurer.campaign.name;

		let actionCell = row.insertCell(5);
		let editButton = document.createElement('button');
		editButton.textContent = 'Edit';
		editButton.addEventListener('click', () => editAdventurer(adventurer.id));

		let deleteButton = document.createElement('button');
		deleteButton.textContent = 'Delete';
		deleteButton.addEventListener('click', () => deleteAdventurer(adventurer.id));

		actionCell.appendChild(editButton);
		actionCell.appendChild(deleteButton);
	});
}

function deleteAdventurer(adventurerId) {
	let confirmed = confirm('Are you sure you want to delete this adventurer?');
	if (confirmed) {
		let xhr = new XMLHttpRequest;

		xhr.open('DELETE', 'api/players/' + currentUser.id + "/adventurers/" + adventurerId);

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status === 204) {
					alert("Adventurer successfully deleted")
				} else if (xhr.status === 404) {
					displayError("Adventurer Not Found");
				} else {
					displayError("There was an Error deleting this adventurer.")
				}
			}
		};

		xhr.send();
	}
	loadAdventurers();
}


function loadCampaigns() {
	let xhr = new XMLHttpRequest;
	xhr.open('GET', 'api/campaigns');

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				let campaigns = JSON.parse(xhr.responseText);
				displayCampaigns(campaigns);
			} else {
				displayError("No Meetings Found");
			}
		}
	};

	xhr.send();
}

function displayCampaigns(campaigns) {
	let campaignTableBody = document.getElementById('campaignTableBody');
	campaignTableBody.innerHTML = '';

	campaigns.forEach(campaign => {
		console.log(campaign);
		let row = campaignTableBody.insertRow();

		let nameCell = row.insertCell(0);
		nameCell.textContent = campaign.name;

		let storytellerCell = row.insertCell(1);
		storytellerCell.textContent = campaign.storyteller.firstName + " " + campaign.storyteller.lastName;

		let gameCell = row.insertCell(2);
		gameCell.textContent = campaign.game.name;

		let actionCell = row.insertCell(3);
		let editButton = document.createElement('button');
		editButton.textContent = 'Edit';
		editButton.addEventListener('click', () => editCampaign(campaign.id));

		actionCell.appendChild(editButton);
	});
}

function addCampaign() {
	let editCampDiv = document.getElementById("editCampaign");
	editCampDiv.style.display = 'block';
	
}

function displayError(errorMsg) {
	alert(errorMsg);
}
