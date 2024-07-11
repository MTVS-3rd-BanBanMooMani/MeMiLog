document.addEventListener("DOMContentLoaded", function () {
  // 미션 주제 모달 관련
  const modalBg = document.querySelector(".modal-bg");
  const closeBtn = document.querySelector(".modal-close-btn");
  const notes = document.querySelectorAll(".note");

  notes.forEach((note) => {
    note.addEventListener("click", function () {
      modalBg.style.display = "flex";
    });
  });

  closeBtn.addEventListener("click", function () {
    modalBg.style.display = "none";
  });

  window.addEventListener("click", function (event) {
    if (event.target === modalBg) {
      modalBg.style.display = "none";
      postModal.style.display = "none";
    }
    if (event.target === postModal) {
      postModal.style.display = "none";
    }
  });

  // 새로운 모달 관련
  const photoDivs = document.querySelectorAll(".photo-div");
  const postModal = document.querySelector(".post-modal");
  const closePostModalBtn = document.querySelector(".post-modal-close-btn");

  // post img
  const modalImages = document.querySelectorAll(".modal-image");
  const arrowLeft = document.querySelector(".arrow-left");
  const arrowRight = document.querySelector(".arrow-right");
  let currentIndex = 0;

  function updateArrows() {
    if (modalImages.length <= 1) {
      arrowLeft.classList.add("hidden");
      arrowRight.classList.add("hidden");
    } else {
      arrowLeft.classList.toggle("hidden", currentIndex === 0);
      arrowRight.classList.toggle(
        "hidden",
        currentIndex === modalImages.length - 1
      );
    }
  }

  photoDivs.forEach((photoDiv) => {
    photoDiv.addEventListener("click", function () {
      postModal.style.display = "block";
      updateArrows();
    });
  });

  closePostModalBtn.addEventListener("click", function () {
    postModal.style.display = "none";
  });

  arrowLeft.addEventListener("click", function () {
    if (currentIndex > 0) {
      modalImages[currentIndex].style.display = "none";
      currentIndex--;
      modalImages[currentIndex].style.display = "block";
      updateArrows();
    }
  });

  arrowRight.addEventListener("click", function () {
    if (currentIndex < modalImages.length - 1) {
      modalImages[currentIndex].style.display = "none";
      currentIndex++;
      modalImages[currentIndex].style.display = "block";
      updateArrows();
    }
  });

  modalImages.forEach((img, index) => {
    img.style.display = index === currentIndex ? "block" : "none";
  });
  updateArrows();

  // etc dropdown
  const etcImg = document.querySelector(".etc-img");
  const dropdownContent = document.querySelector(".dropdown-content");

  etcImg.addEventListener("click", function () {
    dropdownContent.classList.toggle("show");
  });

  // 다른 곳 클릭 시 dropdown 닫기
  window.addEventListener("click", function (event) {
    if (!event.target.matches(".etc-img")) {
      if (dropdownContent.classList.contains("show")) {
        dropdownContent.classList.remove("show");
      }
    }
  });

  // 좋아요 기능
  const likeBtn = document.querySelector(".post-like-btn");

  likeBtn.addEventListener("click", function () {
    if (likeBtn.src.includes("heart.png")) {
      likeBtn.src = "../documentation/img/fullHeart.png";
    } else {
      likeBtn.src = "../documentation/img/heart.png";
    }
  });

  // 삭제 버튼 클릭 시 alert
  const deleteAlert = document.querySelector(".post-delete-btn");

  deleteAlert.addEventListener("click", () => {
    Swal.fire({
      title: "정말로 삭제하시겠습니까?",
      // text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#d33",
      cancelButtonColor: "gray",
      confirmButtonText: "네, 삭제하겠습니다.",
      cancelButtonText: "취소",
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire({
          title: "삭제되었습니다!",
          // text: "Your file has been deleted.",
          icon: "success",
        });
      }
    });
  });

  // 신고 버튼 클릭 시 alert
  const reportAlert = document.querySelector(".post-report-btn");

  reportAlert.addEventListener("click", () => {
    Swal.fire({
      title: "신고 하시겠습니까?",
      icon: "warning",
      input: "select",
      inputOptions: {
        option1: "음란물",
        option2: "광고",
        option3: "종교권유",
      },
      inputPlaceholder: "신고 사유 선택",
      showCancelButton: true,
      confirmButtonColor: "#d33",
      cancelButtonColor: "gray",
      confirmButtonText: "네, 신고하겠습니다.",
      cancelButtonText: "취소",
    });
  });
});
