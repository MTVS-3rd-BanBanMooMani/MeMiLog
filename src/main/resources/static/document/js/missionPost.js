document.addEventListener("DOMContentLoaded", function () {
  // 테마 카테고리
  const categoryBtns = document.querySelectorAll(".category-btn");

  const selectedCategories = [];

  categoryBtns.forEach((btn) => {
    btn.addEventListener("click", () => {
      // 버튼 클릭 시 선택된 카테고리 추가/제거
      btn.classList.toggle("active");
      if (btn.classList.contains("active")) {
        selectedCategories.push(btn.textContent);
        // 취소 버튼 생성
        const cancelBtn = document.createElement("span");
        cancelBtn.classList.add("cancel-btn");
        cancelBtn.innerHTML = "&#10006;";
        btn.appendChild(cancelBtn);
        // 취소 버튼 클릭 시 선택 취소
        cancelBtn.addEventListener("click", () => {
          cancelBtn.classList.remove("active");
          const index = selectedCategories.indexOf(btn.textContent);
          if (index !== -1) {
            selectedCategories.splice(index, 1);
          }
          btn.removeChild(cancelBtn);
        });
      } else {
        const index = selectedCategories.indexOf(btn.textContent);
        if (index !== -1) {
          selectedCategories.splice(index, 1);
        }
        // 취소 버튼 제거
        const cancelBtn = btn.querySelector(".cancel-btn");
        if (cancelBtn) {
          btn.removeChild(cancelBtn);
        }
      }
    });
  });

  // mission note(메모지) 관련
  const noteColors = [
    "#FBD5D5",
    "#F6FDFF",
    "#FFFFF5",
    "#FBF9FF",
    "#F3FFFD",
    "#FFF7FC",
    "#F7FFF3",
    "#FFF8F2",
  ];
  const tapeColors = [
    "#FBD5D5",
    "#D5F4FB",
    "#FBFAD5",
    "#EDD5FB",
    "#D5FBF4",
    "#FBD5EE",
    "#E1FBD5",
    "#FFC692",
  ];

  const notesData = Array.from({ length: 24 }, (_, i) => `Note ${i + 1}`);
  const notesPerPage = 24;
  const noteContainer = document.getElementById("note-container");
  const pagination = document.getElementById("pagination");

  function renderNotes(page) {
    noteContainer.innerHTML = "";
    const start = (page - 1) * notesPerPage;
    const end = start + notesPerPage;
    const notesToDisplay = notesData.slice(start, end);

    notesToDisplay.forEach((noteTitle, noteDate) => {
      const note = document.createElement("div");
      note.className = "note";
      //   note.innerHTML = `
      //       <div class="masking-tape"></div>
      //       <span>${noteDate}</span>
      //       <span>${noteTitle}</span>
      //     `;
      note.innerHTML = `
    <div class="masking-tape"></div>
    <span style="margin: 5px 0;">From. 240617</span>
    <span style="margin: 5px 0;">미미들의 여름 낭만은?</span>
  `;
      const randomIndex = Math.floor(Math.random() * noteColors.length);
      const selectedNoteColor = noteColors[randomIndex];
      const selectedTapeColor = tapeColors[randomIndex];

      note.style.backgroundColor = selectedNoteColor;
      note.querySelector(".masking-tape").style.backgroundColor =
        selectedTapeColor;
      noteContainer.appendChild(note);
    });
  }

  function renderPagination() {
    pagination.innerHTML = "";
    const pageCount = Math.ceil(notesData.length / notesPerPage);

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
});
