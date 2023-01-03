async function loggedIn() {
  let sessionId = getCookie("SESSION_ID");
  let logged = false;

  if (sessionId) {
    const response = await fetch("/users/checkSession?sessionId=" + sessionId, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
    });
    logged = response.ok;
  }

  return logged;
}
  
function getCookie(name) {
  let value = "; " + document.cookie;
  let parts = value.split("; " + name + "=");
  if (parts.length == 2) {
    return parts.pop().split(";").shift();
  }
}

let userId;

function waitForUserId() {
  return new Promise((resolve, reject) => {
    initializeUserId().then(resolve).catch(reject);
  });
}

async function initializeUserId() {
  const isLoggedIn = await loggedIn()
  if (isLoggedIn) {
    await fetch("/users/getBySession?id=" + getCookie("SESSION_ID"))
      .then((response) => response.json())
      .then((data) => {
        userId = data.id;
      });
  } else {
    userId = -1;
  }
}