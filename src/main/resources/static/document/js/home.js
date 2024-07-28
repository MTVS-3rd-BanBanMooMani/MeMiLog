let todayIndex = 0;
const maxPhotos = 7;
let todayPhotos;
let archivePhotos;
let isLoggedIn = false; // 로그인 상태를 저장할 변수

document.addEventListener('DOMContentLoaded', () => {
    // 로그인 여부 확인
    fetch('/user/check')
        .then(response => response.json())
        .then(data => {
            isLoggedIn = data; // 로그인 상태를 변수에 저장
            console.log('로그인 상태:', isLoggedIn); // 디버깅용 로그
        })
        .catch(error => console.error('Error checking login status:', error));

    todayPhotos = Array.from(document.querySelectorAll('#today-photo-container .photo-div'));
    archivePhotos = Array.from(document.querySelectorAll('#archive-photo-container .photo-div'));
    showPhotos(todayPhotos, todayIndex);
    shuffleAndShowArchivePhotos();
    updateArrows(todayIndex, todayPhotos, maxPhotos);
});

function showPhotos(photos, index) {
    photos.forEach((photo, i) => {
        if (i >= index * maxPhotos && i < (index + 1) * maxPhotos) {
            photo.classList.remove('hidden');
            photo.classList.add('visible');
        } else {
            photo.classList.remove('visible');
            photo.classList.add('hidden');
        }
    });
}

function updateArrows(index, photos, maxPhotos) {
    const maxIndex = Math.ceil(photos.length / maxPhotos) - 1;
    const leftArrow = document.getElementById('left-arrow');
    const rightArrow = document.getElementById('right-arrow');
    if (index <= 0) {
        leftArrow.classList.add('disabled');
    } else {
        leftArrow.classList.remove('disabled');
    }
    if (index >= maxIndex) {
        rightArrow.classList.add('disabled');
    } else {
        rightArrow.classList.remove('disabled');
    }
}

window.scrollTodayPhotos = (direction) => {
    const maxIndex = Math.ceil(todayPhotos.length / maxPhotos) - 1;
    todayIndex += direction;
    if (todayIndex < 0) todayIndex = 0;
    if (todayIndex > maxIndex) todayIndex = maxIndex;
    showPhotos(todayPhotos, todayIndex);
    updateArrows(todayIndex, todayPhotos, maxPhotos);
};

function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}

function shuffleAndShowArchivePhotos() {
    shuffleArray(archivePhotos);
    showPhotos(archivePhotos, 0);
}

const modalBg = document.querySelector(".modal-bg");
const photoDivs = document.querySelectorAll(".photo-div");
const postModal = document.querySelector(".post-modal");
const closePostModalBtn = document.querySelector(".post-modal-close-btn");

let currentPostId = null;

// 좋아요 기능
const likeBtn = document.querySelector("#post-like-btn");
const likeCountNum = document.getElementById("likeCount");
let likeCount = null;

photoDivs.forEach((photoDiv) => {
    photoDiv.addEventListener("click", function () {
        if (!isLoggedIn) {
            Swal.fire({
                title: '로그인 필요',
                text: '포스트를 상세하게 보려면 로그인해야 합니다.',
                icon: 'warning',
                confirmButtonText: '확인'
            });
            return;
        }

        modalBg.style.display = 'flex';
        postModal.style.display = 'block';
        updateArrows();
        const postId = photoDiv.getAttribute('data-post-id');
        currentPostId = postId;
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

                // 프로필 이미지
                if(data.profile_img == null) {
                    document.getElementById("profileImg").src = "../../document/img/default_profile.png";
                } else {
                    document.getElementById("profileImg").src = data.profile_img;
                }

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
                const modifyBtn = document.querySelector(".post-edit-btn")

                modifyBtn.addEventListener("click", () => {
                    location.href="/post/update?postId="+postId
                })

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

                // 좋아요 확인
                likeCount = data.like_count;
                // let likeCount = parseInt(likeCountNum.innerText, 10);

                if(data.likeInfo) {
                    likeBtn.src = "../../document/img/fullHeart.png";
                } else {
                    likeBtn.src = "../../document/img/heart.png";
                }
                // 작성자 확인
                if(data.checkUser) {
                    document.querySelector(".post-report-btn").style.display = "none";
                    document.querySelector(".post-edit-btn").style.display = "block";
                    document.querySelector(".post-delete-btn").style.display = "block";
                } else {
                    document.querySelector(".post-report-btn").style.display = "block";
                    document.querySelector(".post-edit-btn").style.display = "none";
                    document.querySelector(".post-delete-btn").style.display = "none";
                }

            })
            .catch(error => console.error("post detail error: ", error));
    });
});

likeBtn.addEventListener("click", function () {
    if(likeBtn.src.includes("heart.png")) {
        console.log("like click postId: ", currentPostId);
        likeBtn.src = "../../document/img/fullHeart.png";
        likeCount++;
        likeFetch(currentPostId);
    } else {
        console.log("dislike click postId: ", currentPostId);
        likeBtn.src = "../../document/img/heart.png";
        likeCount--;
        dislikeFetch(currentPostId);
    }
    likeCountNum.innerText = likeCount;
});

function likeFetch(postId) {
    console.log("likeFetch postId: ", postId);
    fetch("/post/like?post_id=" + postId)
        .then(res => {
            if (!res.ok) {
                throw new Error("Network response was not ok");
            }
            return res.json();
        })
        .then(data => {
            console.log("like data: ", data);
        })
        .catch(error => console.error("like error: ", error));
}

function dislikeFetch(postId) {
    console.log("dislikeFetch postId: ", postId);
    fetch("/post/dislike?post_id=" + postId)
        .then(res => {
            if (!res.ok) {
                throw new Error("Network response was not ok");
            }
            return res.json();
        })
        .then(data => {
            console.log("dislike data: ", data);
        })
        .catch(error => console.error("dislike error: ", error));
}

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
    if (!isLoggedIn) {
        Swal.fire({
            title: '로그인 필요',
            text: '포스트를 상세하게 보려면 로그인해야 합니다.',
            icon: 'warning',
            confirmButtonText: '확인'
        });
        return;
    }

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

// 삭제 버튼 클릭 시 alert
const deleteAlert = document.querySelector(".post-delete-btn");

deleteAlert.addEventListener("click", () => {
    if (!isLoggedIn) {
        Swal.fire({
            title: '로그인 필요',
            text: '포스트를 상세하게 보려면 로그인해야 합니다.',
            icon: 'warning',
            confirmButtonText: '확인'
        });
        return;
    }

    Swal.fire({
        title: "정말로 삭제하시겠습니까?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#d33",
        cancelButtonColor: "gray",
        confirmButtonText: "네, 삭제하겠습니다.",
        cancelButtonText: "취소",
    }).then((result) => {
        if (result.isConfirmed) {
            fetch("/post/delete?postId=" + currentPostId)
                .then(res => {
                    if (res.ok) {
                        Swal.fire({
                            title: "삭제 완료!",
                            icon: "success",
                            showConfirmButton: false,
                            timer: 1500
                        }).then(() => {
                            // 페이지를 다시 로드합니다.
                            location.reload();
                        });
                    } else {
                        Swal.fire({
                            title: "삭제 실패하였습니다!",
                            icon: "error",
                        });
                    }
                })
        }
    });
});
