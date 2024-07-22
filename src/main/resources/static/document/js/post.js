function changeIconColor(icon, id) {
    document.getElementById('selectedEmotion').value = id;
    var icons = document.querySelectorAll('.icon1');
    icons.forEach(function(item) {
        item.src = item.getAttribute('data-original');
    });
    document.getElementById(icon).querySelector('.icon1').src = document.getElementById(icon).querySelector('.icon1').getAttribute('data-colored');
}

function selectCompanion(button) {
    document.getElementById('companionId').value = button.getAttribute('data-id');
    var buttons = document.querySelectorAll('.answer');
    buttons.forEach(function(item) {
        item.classList.remove('selected');
    });
    button.classList.add('selected');
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
    const thumbnail = button.parentElement;
    const thumbnailsContainer = document.getElementById('plus-thumbnail');
    thumbnailsContainer.removeChild(thumbnail);
    updateMainImage();

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