const form = document.getElementById('form');

form.addEventListener('click', input => {
  input.preventDefault();

  const emailInput = document.getElementById('email');
  const email = emailInput.value;

  fetch('users/lookup?email=' + email)
    .then(res => res.json())
    .then(data => {
      if (data.email == email) {
        if (data.enabled) {
          fetch('password-reminder/send-email?email=' + email, {
            method: 'POST',
            headers: {
              Accept: 'application/json',
              'Content-Type': 'application/json',
            }
          });

          location.href = 'index.html';
        }
      }
    });
});
