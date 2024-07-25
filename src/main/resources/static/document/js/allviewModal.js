
// document.addEventListener("DOMContentLoaded", function () {

const selectedCategories = [];

function addValue(button, value) {
  if(!selectedCategories.includes(value)) {
    selectedCategories.push(value);
    button.classList.add("active");
    addCancelButton(button);
    console.log(`selectedCategories: ${selectedCategories}`);
  }
}

function addCancelButton(button) {
  // 취소 버튼 생성
  const cancelBtn = document.createElement("span");
  cancelBtn.classList.add("cancel-btn");
  cancelBtn.innerHTML = "&#10006;";
  cancelBtn.onclick = function (event) {
    event.stopPropagation();
    removeValue(button);
  };
  button.appendChild(cancelBtn);
}

function removeCancelButton(button) {
  const cancelBtn = button.querySelector('.cancel-btn');
  if(cancelBtn) {
    button.removeChild(cancelBtn);
  }
}

function removeValue(button) {
  const value = button.value;
  const index = selectedCategories.indexOf(value);
  if (index !== -1) {
    selectedCategories.splice(index, 1);
    button.classList.remove('active');
    removeCancelButton(button);
    console.log(`selectedCategories: ${selectedCategories}`);
  }
}

async function submitForm() {
  const companionTypeField = document.getElementById('companionType');
  companionTypeField.value = selectedCategories.join(',');
  const form = document.getElementById('companionForm');
  form.submit();
}

document.addEventListener('DOMContentLoaded', () => {
  const params = new URLSearchParams(window.location.search);
  const companionTypes = params.get('type');
  const date = params.get('date');
  if (companionTypes && date) {
    const typesArray = companionTypes.split(',');
    typesArray.forEach(type => {
      const button = document.querySelector(`button[value="${type}"]`);
      if (button) {
        addValue(button, type);
      }
    })
    document.getElementById('date').value = date;
  }
})

// ====================================================================================
  // 미션 주제 모달 관련
  const modalBg = document.querySelector(".modal-bg");
  const photoDivs = document.querySelectorAll(".photo-div");
  const postModal = document.querySelector(".post-modal");
  const closePostModalBtn = document.querySelector(".post-modal-close-btn");

  photoDivs.forEach((photoDiv) => {
    photoDiv.addEventListener("click", function () {
      modalBg.style.display = 'flex';
      postModal.style.display = 'block';
      // updateArrows();
      const postId = photoDiv.getAttribute('data-post-id');
      console.log(postId);
      fetch("/post/bymission", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({postId: postId})
      })
          .then(res => res.json())
          .then(data => {
            console.log("post detail: ", data);

            document.getElementById("priTheme").innerText = data.pri_theme_name;
            document.getElementById("subTheme").innerText = data.sub_theme_name;
            document.getElementById("companion").innerText = data.companion_type;
            document.getElementById("missionContent").innerText = data.mission_content;
            document.getElementById("likeCount").innerText = data.like_count;
            document.getElementById("nickname").innerText = data.nickname;
            document.getElementById("writtenDate").innerText = data.written_datetime;
            document.getElementById("content").innerText = data.content;
            document.getElementById("profileImg").src = data.profile_img;

            const emotionImg = document.getElementById("emotionImg");
            switch (data.emotion_name) {
              case "Happy":
                emotionImg.src = "../../document/img/colored_happy.png";
                break;
              case "Cool":
                emotionImg.src = "../../document/img/colored_cool.png";
                break;
              case "Surprised":
                emotionImg.src = "../../document/img/colored_surprised.png";
                break;
              case "Soso":
                emotionImg.src = "../../document/img/colored_soso.png";
                break;
              case "Sad":
                emotionImg.src = "../../document/img/colored_sad.png";
                break;
              default:
                emotionImg.src = "";
            }

            // Post 이미지 설정
            const postModalBoardImg = document.querySelector(".post-modal-board-img");
            const existingImages = postModalBoardImg.querySelectorAll(".modal-image");
            existingImages.forEach(img => img.remove()); // 기존 이미지를 초기화
            data.postUrl.forEach(url => {
              const img = document.createElement("img");
              img.src = url;
              img.className = 'modal-image';
              postModalBoardImg.insertBefore(img, arrowRight); // 화살표 사이에 이미지를 추가
            });

            // 슬라이드 초기화
            initializeSlideShow();

          })
          .catch(error => console.error("post detail error: ", error));
    });
  });

  closePostModalBtn.addEventListener("click", function () {
    modalBg.style.display = "none";
    postModal.style.display = "none";
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

  // post img
let modalImages = [];
let currentIndex = 0;
const arrowLeft = document.querySelector(".arrow-left");
const arrowRight = document.querySelector(".arrow-right");

// 슬라이드쇼 초기화 함수
function initializeSlideShow() {
  modalImages = document.querySelectorAll(".modal-image");
  currentIndex = 0;

  arrowLeft.addEventListener("click", showPreviousImage);
  arrowRight.addEventListener("click", showNextImage);

  modalImages.forEach((img, index) => {
    img.style.display = index === currentIndex ? "block" : "none";
  });
  updateArrows();
}

// 화살표 업데이트 함수
function updateArrows() {
  if (modalImages.length <= 1) {
    arrowLeft.classList.add("hidden");
    arrowRight.classList.add("hidden");
  } else {
    arrowLeft.classList.toggle("hidden", currentIndex === 0);
    arrowRight.classList.toggle("hidden", currentIndex === modalImages.length - 1);
  }
}

// 이전 이미지 표시 함수
function showPreviousImage() {
  if (currentIndex > 0) {
    modalImages[currentIndex].style.display = "none";
    currentIndex--;
    modalImages[currentIndex].style.display = "block";
    updateArrows();
  }
}

// 다음 이미지 표시 함수
function showNextImage() {
  if (currentIndex < modalImages.length - 1) {
    modalImages[currentIndex].style.display = "none";
    currentIndex++;
    modalImages[currentIndex].style.display = "block";
    updateArrows();
  }
}

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

// 신고 모달 관련
const reportModalBg = document.querySelector(".report-modal-bg");
const reportModal = document.querySelector(".report-modal");
const reportModalCloseBtn = document.querySelector(".report-modal-close-btn");

document.querySelector(".post-report-btn").addEventListener("click", function() {
  console.log("신고버튼 클릭함");
  reportModalBg.style.display = "flex";
  reportModal.style.display = "flex";

  // 기존 게시물 정보 가져오기
  const postId = document.querySelector(".photo-div[data-post-id]").getAttribute('data-post-id');
  fetch("/post/bymission", {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({postId: postId})
  })
      .then(res => res.json())
      .then(data => {
        console.log("신고 데이터: ", data);
        document.getElementById("reportPostId").value = data.post_id;
        document.getElementById("reportedUserId").value = data.user_id;
      })
      .catch(error => console.error("report detail error: ", error));
});

reportModalCloseBtn.addEventListener("click", function () {
  reportModalBg.style.display = "none";
  reportModal.style.display = "none";
});

window.addEventListener("click", function (event) {
  if (event.target === reportModalBg) {
    reportModalBg.style.display = "none";
    reportModal.style.display = "none";
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
// });
