document.addEventListener("DOMContentLoaded", function () {
  const sidebarOpen = document.querySelector(".sidebar-open");
  const sidebarClose = document.querySelector(".sidebar-close");
  const sidebar = document.querySelector(".sidebar");
  const sidebarBg = document.querySelector(".sidebar-bg");

  function openSidebar(event) {
    event.preventDefault();
    sidebar.classList.add("active");
    sidebar.classList.add("animate__animated");
    sidebar.classList.add("animate__slideInRight");
    sidebarBg.style.display = "block";
  }

  function closeSidebar(event) {
    event.preventDefault();
    sidebar.classList.remove("active");
    sidebarBg.style.display = "none";
  }

  sidebarOpen.addEventListener("click", openSidebar);
  sidebarClose.addEventListener("click", closeSidebar);
  sidebarBg.addEventListener("click", closeSidebar); // 배경을 클릭해도 사이드바가 닫히도록 함
});
