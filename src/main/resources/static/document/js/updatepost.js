var fileCount = 0;

document.addEventListener('DOMContentLoaded', function() {
    // 감정 아이콘의 색상을 페이지 로드 시 변경
    let selectedEmotionId = document.getElementById('selectedEmotion').value;
    if (selectedEmotionId) {
        let selectedEmotionElement = document.querySelector('.icon-box li[data-id="' + selectedEmotionId + '"]');
        if (selectedEmotionElement) {
            changeIconColor(selectedEmotionElement.id, selectedEmotionId);
        }
    }

    // 동반자 버튼을 페이지 로드 시 강조
    let selectedCompanionId = document.getElementById('companionId').value;
    if (selectedCompanionId) {
        let selectedCompanionElement = document.querySelector('.answers button[data-id="' + selectedCompanionId + '"]');
        if (selectedCompanionElement) {
            selectCompanion(selectedCompanionElement);
        }
    }

    fetch("/post/oldFile?postId="+document.getElementById('post_id').value.toString())
        .then(res => {
            if (!res.ok) throw new Error('Failed to load post');
            return res.json()
        })
        .then(data => {
            data.forEach((item) => {
                const oldThumbnail = document.createElement('div');
                const oldInputThunmbnail = document.createElement('input');
                oldThumbnail.classList.add('thumbnail');
                oldThumbnail.classList.add("old")
                oldThumbnail.setAttribute('draggable', 'true');
                oldThumbnail.innerHTML = `
                            <img src="${item.src_url}" alt="이미지" onclick="showImageInContainer('${item.src_url}')">
                            <button data-index='${item.picture_order}' class="image-close-btn" onclick="removeThumbnail(this)"></button>
                        `;

                // 지금 생성한 img 태그를 html상에 추가하기 : appendChild()
                oldInputThunmbnail.type = "number";
                oldInputThunmbnail.id = "oldFile"+item.picture_order.toString()
                oldInputThunmbnail.name = "oldFile"+item.picture_order.toString()
                oldInputThunmbnail.style = "display: none";
                oldInputThunmbnail.value = item.picture_id;

                document.querySelector('.image-thumbnails').insertBefore(oldThumbnail, document.querySelector('.add-thumbnail'));
                document.getElementById('oldInputFileContainer').appendChild(oldInputThunmbnail)
                addDragAndDropHandlers(oldThumbnail);

                const div = document.getElementById("plus-thumbnail");
                const remove = document.getElementById("addBtn");
                updateMainImage();
                if (div.children.length === 6) {
                    div.removeChild(remove);
                }
            })
        })
        .catch(error => {
            console.log(error);
        })

    // 이미지 순서 로드
    loadImageOrder();
});

function changeIconColor(iconName, emotionId) {
    document.getElementById('selectedEmotion').value = emotionId;
    let icons = document.querySelectorAll('.icon-box li img');
    icons.forEach(icon => {
        let originalSrc = icon.getAttribute('data-original');
        icon.setAttribute('src', originalSrc);
    });
    let selectedIcon = document.querySelector('.icon-box li[data-id="' + emotionId + '"] img');
    if (selectedIcon) {
        let coloredSrc = selectedIcon.getAttribute('data-colored');
        selectedIcon.setAttribute('src', coloredSrc);
    }
}

function selectCompanion(element) {
    let companionId = element.getAttribute('data-id');
    document.getElementById('companionId').value = companionId;
    let buttons = document.querySelectorAll('.answers button');
    buttons.forEach(button => button.classList.remove('selected'));
    element.classList.add('selected');
}

// Drag and Drop Functions
function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(event) {
    event.preventDefault();
    var data = event.dataTransfer.getData("text");
    var draggedElement = document.getElementById(data);
    var dropTarget = event.target;

    if (dropTarget.classList.contains("thumbnail")) {
        var parent = dropTarget.parentNode;
        parent.insertBefore(draggedElement, dropTarget.nextSibling);
        updateImageOrder();
    }
}

function updateImageOrder() {
    var thumbnails = document.querySelectorAll(".image-thumbnails .thumbnail");
    var imageOrder = [];
    thumbnails.forEach(function(thumbnail, index) {
        if (thumbnail.id !== "addBtn") {
            imageOrder.push(thumbnail.id + ":" + (index + 1));
        }
    });
    document.getElementById("imageOrder").value = imageOrder.join(",");
}

function loadImageOrder() {
    let storedOrder = localStorage.getItem('imageOrder');
    if (storedOrder) {
        let newOrder = JSON.parse(storedOrder);
        let thumbnails = document.querySelectorAll('.image-thumbnails .thumbnail img');
        newOrder.forEach((item, index) => {
            if (thumbnails[index]) {
                thumbnails[index].src = item.srcUrl;
            }
        });
    }
}

function prepareImageOrder() {
    let thumbnails = document.querySelectorAll('.image-thumbnails .thumbnail img');
    let imageOrder = [];
    thumbnails.forEach((thumbnail, index) => {
        imageOrder.push({ srcUrl: thumbnail.src, order: index + 1 });
    });
    document.getElementById('imageOrder').value = JSON.stringify(imageOrder);
}
function toggleSelection(element) {
    var buttons = document.querySelectorAll('.answers .answer');
    buttons.forEach(function(button) {
        button.classList.remove('selected');
    });
    element.classList.add('selected');
}

document.getElementById('addBtn').addEventListener('click', function() {
    document.getElementById('imageUpload').click();
});

document.getElementById('imageUpload').addEventListener('change', function(event) {
    const files = event.target.files;
    fileCount++;
    console.log(fileCount)
    if (files.length > 0) {
        const reader = new FileReader();
        reader.onload = function(e) {
            const newThumbnail = document.createElement('div');
            const newInputThunmbnail = document.createElement('input');
            newThumbnail.classList.add('thumbnail');
            newThumbnail.classList.add('new')
            newThumbnail.setAttribute('draggable', 'true');
            newThumbnail.innerHTML = `
                <img src="${e.target.result}" alt="이미지" onclick="showImageInContainer('${e.target.result}')">
                <button data-index='${fileCount}' class="image-close-btn" onclick="removeThumbnail(this)"></button>
            `;
            newInputThunmbnail.type = 'file';
            newInputThunmbnail.id = "newFile"+fileCount.toString();
            newInputThunmbnail.name = "newFile"+fileCount.toString();
            //
            const dataTransfer = new DataTransfer();

            dataTransfer.items.add(event.target.files[0])
            newInputThunmbnail.style.display = 'none';

            newInputThunmbnail.files = dataTransfer.files;

            // 지금 생성한 img 태그를 html상에 추가하기 : appendChild()

            document.querySelector('.image-thumbnails').insertBefore(newThumbnail, document.querySelector('.add-thumbnail'));
            document.querySelector('#newInputFileContainer').appendChild(newInputThunmbnail);
            addDragAndDropHandlers(newThumbnail);

            const div = document.getElementById("plus-thumbnail");
            const remove = document.getElementById("addBtn");
            updateMainImage();
            if (div.children.length === 6) {
                div.removeChild(remove);
            }
        };
        reader.readAsDataURL(files[0]);
    }
});



function updateMainImage() {
    const thumbnailsContainer = document.getElementById('plus-thumbnail');
    const firstThumbnail = thumbnailsContainer.querySelector('.thumbnail img');
    if (firstThumbnail) {
        showImageInContainer(firstThumbnail.src);
    }else {
        showImageInContainer('');
    }
}

function showImageInContainer(imageSrc) {
    const mainImage = document.getElementById('main-image');
    mainImage.src = imageSrc;
}

function removeThumbnail(button) {
    fileCount--;
    const thumbnail = button.parentElement;
    const thumbnailsContainer = document.getElementById('plus-thumbnail');
    thumbnailsContainer.removeChild(thumbnail);
    updateMainImage();

    var dataIndex = button.getAttribute('data-index').toString();

    if(thumbnail.classList.contains('old')) {
        // 이전 사진의 경우 oldInputContainer에서 삭제
        const oldInputContainer = document.getElementById('oldInputFileContainer');
        const oldInput = document.getElementById("oldFile"+dataIndex)
        oldInputContainer.removeChild(oldInput)

    } else if(thumbnail.classList.contains('new')) {
        // 새로운 사진의 경우 newInputContainer에서 삭제
        const newInputContainer = document.getElementById('newInputFileContainer');
        const newInput = document.getElementById("newFile"+dataIndex)
        newInputContainer.removeChild(newInput)
    }

    // input type=file 없애는 로직
    // const inputFileContainer = document.getElementById('inputFileContainer');
    // const inputFile = document.getElementsByName('newfile'+button.getAttribute('data-index').toString())
    // inputFileContainer.removeChild(inputFile)
    // console.log(inputFile)

    if (thumbnailsContainer.children.length < 6) {
        if (!document.getElementById('addBtn')) {
            const addBtn = document.createElement('div');
            addBtn.classList.add('thumbnail', 'add-thumbnail');
            addBtn.id = 'addBtn';
            addBtn.innerHTML = '+<input type="file" id="imageUpload" accept="image/*" style="display: none;">';
            thumbnailsContainer.appendChild(addBtn);

            document.querySelector('.add-thumbnail').addEventListener('click', function() {
                document.getElementById('imageUpload').click();
            });

            document.getElementById('imageUpload').addEventListener('change', function(event) {
                const files = event.target.files;
                if (files.length > 0) {
                    const reader = new FileReader();
                    reader.onload = function(e) {
                        const newThumbnail = document.createElement('div');
                        newThumbnail.classList.add('thumbnail');
                        newThumbnail.setAttribute('draggable', 'true');
                        newThumbnail.innerHTML = `
                            <img src="${e.target.result}" alt="이미지" onclick="showImageInContainer('${e.target.result}')">
                            <button class="image-close-btn" onclick="removeThumbnail(this)"></button>
                        `;
                        document.querySelector('.image-thumbnails').insertBefore(newThumbnail, document.querySelector('.add-thumbnail'));
                        addDragAndDropHandlers(newThumbnail);
                        updateMainImage();

                        if (thumbnailsContainer.children.length >= 6) {
                            document.getElementById('addBtn').remove();
                        }
                    };
                    reader.readAsDataURL(files[0]);
                }
            });
        }
    }
}

function addDragAndDropHandlers(element) {
    element.addEventListener('dragstart', handleDragStart);
    element.addEventListener('dragover', handleDragOver);
    element.addEventListener('drop', handleDrop);
    element.addEventListener('dragend', handleDragEnd);
}

let draggedElement = null;

function handleDragStart(event) {
    draggedElement = event.target.closest('.thumbnail')
    event.dataTransfer.effectAllowed = 'move';
    event.dataTransfer.setData('text/html', draggedElement.outerHTML);
    setTimeout(() => {
        draggedElement.style.opacity = '0.5';
        draggedElement.style.border = '1px dashed #ccc';
    }, 0);
}

function handleDragOver(event) {
    event.preventDefault();
    event.dataTransfer.dropEffect = 'move';
}

function handleDrop(event) {
    event.preventDefault();
    event.stopPropagation();

    const targetElement = event.target.closest('.thumbnail');
    const parentElement = document.getElementById('plus-thumbnail');

    if (draggedElement && targetElement && targetElement !== draggedElement && !targetElement.classList.contains('add-thumbnail')) {
        const draggedIndex = Array.from(parentElement.children).indexOf(draggedElement);
        const targetIndex = Array.from(parentElement.children).indexOf(targetElement);

        if (draggedIndex < targetIndex) {
            parentElement.insertBefore(draggedElement, targetElement.nextSibling);
        } else {
            parentElement.insertBefore(draggedElement, targetElement);
        }
        updateMainImage();
    }

    draggedElement.style.opacity = '1';
    draggedElement.style.border = 'none';
    draggedElement = null;
}

function handleDragEnd(event) {
    if (draggedElement) {
        draggedElement.style.opacity = '1';
        draggedElement.style.border = 'none';
    }
    draggedElement = null;
}

document.querySelectorAll('.thumbnail[draggable="true"]').forEach(function(element) {
    addDragAndDropHandlers(element);
    updateMainImage();
});
document.querySelector('.submit-btn').addEventListener('click', function() {
    var modal = document.getElementById("myModal");
    var span = document.getElementsByClassName("close")[0];

    // 모달 창 열기
    modal.style.display = "block";

    // 닫기 버튼 클릭 시 모달 창 닫기
    span.onclick = function() {
        modal.style.display = "none";
    }

    // 모달 창 외부 클릭 시 모달 창 닫기
    window.onclick = function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
});