const form = document.getElementById('form');

form.addEventListener('click', input => {
  input.preventDefault();

  const password = document.getElementById('password').value;
  if(checkPasswords())
  {
    fetch('password-reminder/change-password?newPassword=' + password, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        }
      });

      location.href = 'index.html';
  }
});

function checkPasswords() {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm_password").value;
    if (password != confirmPassword) {
        alert("Passwords do not match!");
        return false;
    }
    return true;
}
