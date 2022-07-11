function button_clicked(evt)
{
  alert("Clicked!");
}

window.onload = function()
{
  document.querySelector('button.hmmm')
    .addEventListener('click', button_clicked);
}
