const categoryBtns = document.querySelectorAll(".category-btn");

const selectedCategories = [];
const params = new URLSearchParams(window.location.search);
const word = params.get('word');

console.log(word);

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
  const themeTypeField = document.getElementById('themeType');
  themeTypeField.value = selectedCategories.join(',');

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
      notesData = data;
      renderNotes(1);
      renderPagination();
    } else {
      console.error("Expected JSON response, got:", await response.text());
    }
  } catch (error) {
    console.error("Error fetching data:", error);
  }
}

document.getElementById('themeWordForm').addEventListener('submit', (event) => {
  event.preventDefault();
  submitForm();
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
});

const noteColors = [
  "#fffafa", "#F6FDFF", "#FFFFF5", "#FBF9FF", "#F3FFFD", "#FFF7FC", "#F7FFF3", "#FFF8F2",
];
const tapeColors = [
  "#FBD5D5", "#D5F4FB", "#FBFAD5", "#EDD5FB", "#D5FBF4", "#FBD5EE", "#E1FBD5", "#FFC692",
];

const noteContainer = document.getElementById("note-container");
const pagination = document.getElementById("pagination");

let notesData = [];
const notesPerPage = 24;

if (word) {
  fetch(`/theme?word=${word}`)
      .then(response => response.json())
      .then(data => {
        console.log("검색 mission: ", data);
        notesData = data; // notesData를 업데이트
        renderNotes(1);
        renderPagination();
      })
      .catch(error => console.error('Error fetching data:', error));
} else {
  fetch("/main/allMission")
      .then(res => res.json())
      .then(data => {
        console.log("Fetched data:", data);
        notesData = data;
        renderNotes(1);
        renderPagination();
      })
      .catch(error => console.error("Error fetching data:", error));
}

function renderNotes(page) {
  noteContainer.innerHTML = "";
  const start = (page - 1) * notesPerPage;
  const end = start + notesPerPage;
  const notesToDisplay = notesData.slice(start, end);

  notesToDisplay.forEach(note => {
    const [year, month, day] = note.missionDate.split('-');
    const formattedDate = `FROM.${year.slice(2)}${month}${day}`;

    const noteElement = document.createElement("div");
    noteElement.className = "note";
    noteElement.innerHTML = `
                        <div class="masking-tape"></div>
                        <span>${formattedDate}</span>
                        <span>${note.missionContent}</span>
                    `;
    const randomIndex = Math.floor(Math.random() * noteColors.length);
    const selectedNoteColor = noteColors[randomIndex];
    const selectedTapeColor = tapeColors[randomIndex];

    noteElement.style.backgroundColor = selectedNoteColor;
    noteElement.querySelector(".masking-tape").style.backgroundColor = selectedTapeColor;
    noteElement.addEventListener("click", () => {
      location.href = "/post/bymission?date="+note["missionDate"];
    });
    noteContainer.appendChild(noteElement);
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