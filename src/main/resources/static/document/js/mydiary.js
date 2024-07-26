console.log("Script Loaded");

var barChart;
var lineChart;

document.addEventListener("DOMContentLoaded", function () {
    const firstTabLink = document.querySelector(".tablinks");
    const dateSelector = document.querySelector(".date-selector ul");

    const allPosts = JSON.parse(document.getElementById("posts-data").textContent);

    let currentIndex = 0;
    const batchSize = 10;

    const postCardContainer = document.getElementById("post-card-container");

    loadMorePosts();

    postCardContainer.addEventListener('scroll', function () {
        if (postCardContainer.scrollTop + postCardContainer.clientHeight >= postCardContainer.scrollHeight) {
            loadMorePosts();
        }
    });

    function loadMorePosts() {
        const postsToLoad = allPosts.slice(currentIndex, currentIndex + batchSize);
        displayPosts(postsToLoad);
        currentIndex += batchSize;
    }

    function displayPosts(posts) {
        posts.forEach(post => {
            const postCard = document.createElement("div");
            postCard.className = "post-card";
            postCard.dataset.postId = post.post_id;

            const postImage = document.createElement("img");
            postImage.src = post.src_url;
            postImage.alt = "post image";

            postCard.appendChild(postImage);
            postCardContainer.appendChild(postCard);

            postCard.addEventListener("click", function (){
                showPostModal(post.post_id);
            });
        });
    }

    if (firstTabLink) {
        firstTabLink.click();
    }

    dateSelector.addEventListener("click", function (event) {
        if (event.target.tagName === "LI") {
            const selectedDate = event.target.textContent;
            fetchPostsForDate(selectedDate);
        }
    });
});

function openPage(evt, pageName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(pageName).style.display = "flex";
    evt.currentTarget.className += " active";
    if (pageName === 'post-card-container') {
        document.querySelector('.total-mood').style.display = 'block';
        document.querySelector('.user-mood-chart').style.display = 'none';
        displayBarChart();
    } else if (pageName === 'calendar-container') {
        document.querySelector('.total-mood').style.display = 'none';
        document.querySelector('.user-mood-chart').style.display = 'block';
        document.querySelector('.calendar-contents').style.display = 'flex';
        displayLineChart();
    }
}

function openModal(modalId) {
    document.getElementById(modalId).style.display = "block";
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = "none";
}

document.getElementById('profilePicForm').onsubmit = function (event) {
    event.preventDefault();

    var formData = new FormData(this);

    $.ajax({
        url: '/file/upload',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(response) {
            var userConfirmed = confirm("프로필 사진이 성공적으로 변경되었습니다.");
            if (userConfirmed) {
                location.reload();
            }
        },
        error: function(xhr, status, error) {
            console.log("Error: " + error);
            alert("프로필 사진 변경에 실패했습니다.");
        }
    });
}

function deleteProfilePic() {
    var user_id = document.querySelector('input[name="user_id"]').value;
    var type = "profile";
    $.ajax({
        url: 'file/resetPic',
        type: 'POST',
        data: {
            user_id: user_id,
            type: type
        },
        success: function (response){
            var defaultSrc = document.querySelector('.profile-pic').getAttribute('data-default-src');
            document.querySelector('.profile-pic').src = defaultSrc;
            var userConfirmed = confirm("프로필 사진이 성공적으로 삭제되었습니다.");
            if (userConfirmed) {
                location.reload();
            }
        },
        error: function(xhr, status, error){
            alert("프로필 사진 삭제에 실패했습니다. 다시 시도해주세요.")
        }
    });
}

window.onclick = function (event) {
    var modals = document.getElementsByClassName('modal');
    for (var i = 0; i < modals.length; i++) {
        if (event.target == modals[i]) {
            modals[i].style.display = "none";
        }
    }
}

document.getElementById('editForm').onsubmit = function(event) {
    event.preventDefault();
    const password = document.getElementById('password').value;
    const password2 = document.getElementById('password2').value;
    if (password !== password2) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
    }
    var formData = $(this).serialize();
    $.ajax({
        url: '/editUserInfo',
        type: 'POST',
        data: formData,
        success: function(response) {
            $('#userModal #userEmail').text(response.email);
            $('#userModal #userNickname').text(response.nickname);
            $('#userNickname').text(response.nickname);
            alert("회원 정보가 성공적으로 수정되었습니다.");
            closeModal('editModal');
        },
        error: function(xhr, status, error) {
            console.log("Error: " + error);
        }
    });
};

document.getElementById('deleteForm').onsubmit = function(event) {
    event.preventDefault();

    const password = document.getElementById('deletePassword').value;

    fetch('/user/withdraw', {
        method: 'POST',
        headers: {
            'Content-Type': 'text/plain'
        },
        body: password
    })
        .then(response => {
            return response.text().then(text => {
                if (response.ok) {
                    alert(text);
                    closeModal('deleteModal');
                    location.href = '/';
                } else {
                    throw new Error(text);
                }
            });
        })
        .catch(error => {
            alert(error.message);
            closeModal('deleteModal');
        });
};

function sendDate(day) {
    const selectedDate = day;
    fetchPostsForDate(selectedDate);
}

function fetchPostsForDate(date) {
    fetch("/selectedDate", {
        method : "POST",
        headers : {
            "Content-Type" : "application/json"
        },
        body: JSON.stringify({ selectedDate: date })
    })
        .then(response => response.json())
        .then(data => {
            displayPosts(data);
        })
        .catch(error => console.error("Error fetching posts: " , error));
}

function displayPosts(posts) {
    const calendarContents = document.querySelector(".calendar-contents");
    calendarContents.innerHTML = "";

    posts.forEach(post => {
        const postElement = document.createElement("div");
        postElement.className = "calendar-card";
        postElement.dataset.postId = post.post_id;

        const postImage = document.createElement("img");
        postImage.src = `${post.src_url}`;
        postImage.width = 150;
        postImage.height = 180;

        postElement.appendChild(postImage);
        calendarContents.appendChild(postElement);

        postElement.addEventListener("click",function (){
            showPostModal(post.post_id);
        });
    });
}

function showPostModal(postId){
    const modalBg = document.querySelector('.modal-bg');
    const postModal = document.querySelector('.post-modal');

    modalBg.style.display = 'flex';
    postModal.style.display = 'block';

    fetch("/post/bymission", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ postId: postId })
    })
        .then(res => res.json())
        .then(data => {
            console.log("post detail: ", data);

            document.getElementById("priTheme").innerText = data.pri_theme_name;
            document.getElementById("subTheme").innerText = data.sub_theme_name;
            document.getElementById("companion").innerText = data.companion_type;
            document.getElementById("missionContent").innerText = data.mission_content;
            document.getElementById("likeCount").innerText = data.like_count;
            document.getElementById("modalNickname").innerText = data.nickname;
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

            const postModalBoardImg = document.querySelector(".post-modal-board-img");
            const existingImages = postModalBoardImg.querySelectorAll(".modal-image");
            existingImages.forEach(img => img.remove()); // Remove existing images

            const arrowLeft = document.querySelector(".arrow-left");
            const arrowRight = document.querySelector(".arrow-right");

            if (data.postUrl && data.postUrl.length > 0) {
                data.postUrl.forEach(url => {
                    const img = document.createElement("img");
                    img.src = url;
                    img.className = 'modal-image';
                    postModalBoardImg.insertBefore(img, arrowRight); // Insert images
                });
            } else {
                const noImageMessage = document.createElement("p");
                noImageMessage.textContent = "이미지가 없습니다.";
                postModalBoardImg.insertBefore(noImageMessage, arrowRight);
            }

            initializeSlideShow(); // Initialize slideshow for images
        })
        .catch(error => console.error("post detail error: ", error));

    const modifyBtn = document.querySelector(".post-edit-btn");
    modifyBtn.addEventListener("click", () => {
        location.href = "/post/update?postId=" + postId;
    });

    const deleteAlert = document.querySelector(".post-delete-btn");
    deleteAlert.addEventListener("click", () => {
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
                fetch("/post/delete?postId=" + postId)
                    .then(res => {
                        if (res.ok) {
                            location.href = "/post/bymission";
                        } else {
                            Swal.fire({
                                title: "삭제 실패하였습니다!",
                                icon: "error",
                            });
                        }
                    });
            }
        });
    });
}

async function fetchEmotionCounts() {
    try {
        const response = await fetch('/emotions?emotion_ids=1,2,3,4,5');

        if (!response.ok) {
            throw new Error('Failed to fetch emotion counts');
        }
        const emotionCounts = await response.json();
        updateBarChart(emotionCounts);
    } catch (error) {
        console.error('Error:', error);
    }
}

function displayBarChart() {
    fetchEmotionCounts();
}

function updateBarChart(emotionCounts) {
    const labels = [
        { img: '../../document/img/colored_happy.png', text: '😃', id: 1 },
        { img: '../../document/img/colored_cool.png', text: '😎', id: 2 },
        { img: '../../document/img/colored_surprised.png', text: '😮', id: 3 },
        { img: '../../document/img/colored_soso.png', text: '😐', id: 4 },
        { img: '../../document/img/colored_sad.png', text: '🥲', id: 5 }
    ];

    const data = labels.map(label => emotionCounts[label.id] || 0);

    if (barChart) {
        barChart.destroy();
        barChart = null;
    }

    var barChartCanvas = document.getElementById("bar-chart-horizontal");
    barChartCanvas.height = 240;
    barChartCanvas.width = 400;
    var ctx = barChartCanvas.getContext("2d");

    ctx.clearRect(0, 0, barChartCanvas.width, barChartCanvas.height);


    var images = [];
    var imagesLoaded = 0;

    labels.forEach((label, index) => {
        var image = new Image();
        image.src = label.img;
        image.onload = () => {
            imagesLoaded++;
            if (imagesLoaded === labels.length) {
                drawBarChart();
            }
        };
        images.push(image);
    });

    function drawBarChart() {
        barChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels.map(label => label.text),
                datasets: [{
                    backgroundColor: ["#8D7CB3", "#83A1B5", "#B39F7F", "#98B681", "#AE7E8A"],
                    data: data
                }]
            },
            options: {
                indexAxis: 'y',
                plugins: {
                    title: {
                        display: true,
                        text: 'My Whole Mood',
                        align: 'center',
                        font: {
                            size: 20,
                            weight: 'normal',
                            family: 'Yeon Sung'
                        },
                        padding: 20
                    },
                    legend: {
                        display: false
                    },
                },
                layout: {
                    padding: {
                        left: 30
                    }
                },
                scales: {
                    x: {
                        ticks: {
                            beginAtZero: true
                        },
                        title: {
                            display: true,
                            font: {
                                size: 20,
                                family: 'Yeon Sung'
                            }
                        }
                    },
                    y: {
                        ticks: {
                            display: true,
                            callback: function(value, index) {
                                return '';
                            }
                        },
                        barPercentage: 0.6,
                        categoryPercentage: 1.8
                    }
                }
            },
            plugins: [{
                afterDraw: chart => {
                    var ctx = chart.ctx;
                    chart.data.labels.forEach((label, index) => {
                        var image = images[index];
                        var y = chart.scales.y.getPixelForValue(index) - 15;
                        var x = chart.scales.x.left - 40;
                        ctx.drawImage(image, x, y, 30, 30);
                    });
                }
            }]
        });
    }
}

async function fetchMonthlyEmotionCounts(){
    try {
        const response = await fetch('/monthlyemotions?emotion_ids=1,2,3,4,5&months=1,2,3,4,5,6,7,8,9,10,11,12');

        if(!response.ok){
            throw new Error('Failed to fetch monthly emotion counts');
        }
        const monthlyEmotionCounts = await response.json();
        console.log('Fetched data: ', monthlyEmotionCounts);
        updateLineChart(monthlyEmotionCounts);
    } catch (error) {
        console.error('Error: ', error)
    }
}

function displayLineChart() {
    fetchMonthlyEmotionCounts();
}

function updateLineChart(monthlyEmotionCounts = {}){

    console.log('Monthly Emotion Counts:', monthlyEmotionCounts);

    const labels = [
        { id: 1, label: "😀", borderColor: "#8D7CB3" },
        { id: 2, label: "😎", borderColor: "#83A1B5" },
        { id: 3, label: "😮", borderColor: "#B39F7F" },
        { id: 4, label: "😐", borderColor: "#98B681" },
        { id: 5, label: "😢", borderColor: "#AE7E8A" }
    ];

    const datasets = labels.map(label => ({
        data: [],
        label: label.label,
        borderColor: label.borderColor,
        fill: false
    }));

    labels.forEach(label => {
        for(let month = 1; month <=12; month++){
            datasets.find(dataset => dataset.label === label.label).data[month - 1] = monthlyEmotionCounts[label.id] && monthlyEmotionCounts[label.id][month] || 0;
        }
    });

    console.log('Datasets: ', datasets);

    if(lineChart){
        lineChart.destroy();
        lineChart = null;
    }

    var lineChartCanvas = document.getElementById("line-chart").getContext("2d");
    drawLineChart(lineChartCanvas, datasets);
}

function drawLineChart(ctx, datasets) {
    lineChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
            datasets: datasets
        },
        options: {
            plugins: {
                title: {
                    display: true,
                    text: 'Track My Mood',
                    font: {
                        size: 20,
                        weight: 'normal',
                        family: 'Yeon Sung'
                    },
                    padding: {
                        top: 10,
                        bottom: 30
                    }
                },
                legend: {
                    display: false
                }
            },
            scales: {
                y: {
                    ticks: {
                        display: false
                    }
                }
            }
        },
        plugins: [{
            afterRender: function(chart) {
                const legendContainer = document.getElementById('legend');
                legendContainer.innerHTML = '';
                chart.data.datasets.forEach((dataset, i) => {
                    const legendItem = document.createElement('div');
                    legendItem.classList.add('legend-item');
                    legendItem.onclick = function() {
                        const meta = chart.getDatasetMeta(i);
                        meta.hidden = meta.hidden === null ? !chart.data.datasets[i].hidden : null;
                        chart.update();
                    };
                    const colorBox = document.createElement('span');
                    colorBox.classList.add('color-box');
                    colorBox.style.borderColor = dataset.borderColor;
                    const img = document.createElement('img');
                    img.src = getEmojiImage(dataset.label);
                    img.classList.add('legend-image');
                    legendItem.appendChild(colorBox);
                    legendItem.appendChild(img);
                    legendContainer.appendChild(legendItem);
                });
            }
        }]
    });
}


function getEmojiImage(emoji) {
    switch (emoji) {
        case "😀": return '../../document/img/colored_happy.png';
        case "😎": return '../../document/img/colored_cool.png';
        case "😮": return '../../document/img/colored_surprised.png';
        case "😐": return '../../document/img/colored_soso.png';
        case "😢": return '../../document/img/colored_sad.png';
        default: return '';
    }
}