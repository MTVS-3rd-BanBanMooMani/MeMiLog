
// document.addEventListener("DOMContentLoaded", function () {
  // 테마 카테고리
  const categoryBtns = document.querySelectorAll(".category-btn");

  const selectedCategories = [];

  function addValue(button, value) {
    if(!selectedCategories.includes(value)) {
      const inputTag = document.getElementById('themeType');
      selectedCategories.push(value);
      inputTag.value = selectedCategories.toString();
      button.classList.add("active");
      addCancelButton(button);
    } else {
      removeValue(button);
    }
    console.log(`selectedCategories: ${selectedCategories}`);
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
    // 숨은 필드에 선택한 카테고리 값을 설정
    const themeTypeField = document.getElementById('themeType');
    themeTypeField.value = selectedCategories.join(',');

    // 폼 데이터를 준비하고 비동기 요청을 보냄
    const form = document.getElementById('themeWordForm');
    const queryString = new URLSearchParams(new FormData(form)).toString();
    const url = `/theme?${queryString}`;

    try {
      const response = await fetch(url, {
        headers: {
          'Accept': 'application/json'
        }
      });

      if (response.headers.get('content-type')?.includes('application/json')) {
        const data = await response.json();
        console.log("검색 mission: ", data);
        notesData = data; // notesData를 업데이트
        renderNotes(1);
        renderPagination();
      } else {
        console.error("Expected JSON response, got:", await response.text());
      }
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  }

// 폼 제출 이벤트 리스너 추가
document.getElementById('themeWordForm').addEventListener('submit', (event) => {
  event.preventDefault(); // 기본 폼 제출 동작을 막음
  submitForm(); // 비동기 요청을 보내는 submitForm 호출
});

  document.addEventListener('DOMContentLoaded', () => {
    const params = new URLSearchParams(window.location.search);
    const themeTypes = params.get('type');
    if (themeTypes) {
      const typesArray = themeTypes.split(',');
      typesArray.forEach(type => {
        const button = document.querySelector(`button[value="${type}"]`);
        if (button) {
          addValue(button, type);
        }
      })
    }
  })

  // ==================================================================================
  // mission note(메모지) 관련
  // 메모지와 마스킹테이프 색깔
  const noteColors = [
    "#fffafa", "#F6FDFF", "#FFFFF5", "#FBF9FF", "#F3FFFD", "#FFF7FC", "#F7FFF3", "#FFF8F2",
  ];
  const tapeColors = [
    "#FBD5D5", "#D5F4FB", "#FBFAD5", "#EDD5FB", "#D5FBF4", "#FBD5EE", "#E1FBD5", "#FFC692",
  ];

// 메모지 div 추가할 요소
  const noteContainer = document.getElementById("note-container");
// 페이지 버튼 표시할 요소
  const pagination = document.getElementById("pagination");

// 메모지 데이터 저장할 배열
  let notesData = [];
// 한 페이지에 표시할 메모지의 개수
  const notesPerPage = 24;

// 메모지 데이터 받아오기
  fetch("/main/allMission")
      .then(res => res.json()) // 응답을 JSON 형식으로 변환
      // 데이터를 성공적으로 받았을 때,
      .then(data => {
        console.log("Fetched data:", data);
        notesData = data;
        renderNotes(1);
        renderPagination();
      })
      .catch(error => console.error("Error fetching data:", error));

  // 메모지(노트) 렌더링 함수
  // 주어진 페이지 번호에 해당하는 메모지들을 화면에 렌더링
  function renderNotes(page) {
    noteContainer.innerHTML = "";
    const start = (page - 1) * notesPerPage;
    const end = start + notesPerPage;
    const notesToDisplay = notesData.slice(start, end);

    // 각 메모지들을 화면에 렌더링 함
    notesToDisplay.forEach(note => {
      // console.log("Rendering note:", note);

      // 날짜 변환
      const [year, month, day] = note.missionDate.split('-');
      const formattedDate = `FROM.${year.slice(2)}${month}${day}`;

      const noteElement = document.createElement("div");
      noteElement.className = "note";
      noteElement.innerHTML = `
            <div class="masking-tape"></div>
            <span>${formattedDate}</span>
            <span>${note.missionContent}</span>
        `;
      // 메모지 무작위 색상
      const randomIndex = Math.floor(Math.random() * noteColors.length);
      const selectedNoteColor = noteColors[randomIndex];
      const selectedTapeColor = tapeColors[randomIndex];

      noteElement.style.backgroundColor = selectedNoteColor;
      noteElement.querySelector(".masking-tape").style.backgroundColor = selectedTapeColor;
      noteElement.addEventListener("click", () => {
        // location.href = "/allmission?post_id="+note["missionId"]
        location.href = "/post/bymission?date="+note["missionDate"]
      })
      noteContainer.appendChild(noteElement);

    });
  }

// ==================================================================================

// 페이지네이션 렌더링 함수
  function renderPagination() {
    pagination.innerHTML = "";
    // 필요한 총 페이지 수를 계산함
    const pageCount = Math.ceil(notesData.length / notesPerPage);

    // 각 페이지에 대한 버튼 생성
    for (let i = 1; i <= pageCount; i++) {
      const pageBtn = document.createElement("div");
      pageBtn.className = "page-btn";
      pageBtn.textContent = i;
      if (i === 1) pageBtn.classList.add("active");
      pageBtn.addEventListener("click", function () {
        document.querySelectorAll(".page-btn").forEach((btn) => {
          btn.classList.remove("active");
        });
        pageBtn.classList.add("active");
        renderNotes(i);
      });
      pagination.appendChild(pageBtn);
    }
  }

  renderNotes(1);
  renderPagination();
// });

function likeFetch() {
  // const postIdValue = document.getElementById('id').value; 나중에 여기에 값 넣어서 주석 제거하고 사용하시면 됩니다.
  const postIdValue = 49
  fetch("/post/like?post_id="+postIdValue)
      .then(res => res.json())
      .then(data => {
        console.log(data);
      })
}

function dislikeFetch() {
  // const postIdValue = document.getElementById('id').value; 나중에 여기에 값 넣어서 주석 제거하고 사용하시면 됩니다.
  const postIdValue = 49
  fetch("/post/dislike?post_id="+postIdValue)
      .then(res => res.json())
      .then(data => {
        console.log(data);
      })
}