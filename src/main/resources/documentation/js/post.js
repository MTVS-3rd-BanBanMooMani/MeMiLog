function changeIconColor(id) {
    // 모든 아이콘을 초기 상태로 되돌리기
    var icons = document.querySelectorAll('.icon-box li');
    icons.forEach(function(li) {
        var iconId = li.id;
        li.querySelector('img').setAttribute('src', `../documentation/img/${iconId}.png`);
        li.setAttribute('onclick', `changeIconColor('${iconId}')`);
    });

    // 선택된 아이콘만 색상 변경
    var selectedLi = document.getElementById(id);
    selectedLi.querySelector('img').setAttribute('src', `../documentation/img/colored_${id}.png`);
    selectedLi.setAttribute('onclick', `resetIconColor('${id}')`);
}

function toggleSelection(element) {
    var buttons = document.querySelectorAll('.answers .answer');
    buttons.forEach(function(button) {
        button.classList.remove('selected');
    });
    element.classList.add('selected');
}

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

            var div = document.getElementById("plus-thumbnail");
            var remove = document.getElementById("addBtn");
            if (div.children.length === 6) {
                div.removeChild(remove);
            }
            if (div.children.length === 2) {
                showImageInContainer(e.target.result);
            }
        };
        reader.readAsDataURL(files[0]);
    }
});

function showImageInContainer(imageSrc) {
    const imageContainer = document.querySelector('.image-container');
    imageContainer.innerHTML = `
        <img src="${imageSrc}" alt="확대된 이미지" class="main-image">
    `;
}

function removeThumbnail(button) {
    const thumbnail = button.parentElement;
    const thumbnailsContainer = document.getElementById('plus-thumbnail');
    thumbnailsContainer.removeChild(thumbnail);

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
                            <img src="${e.target.result}" alt="이미지">
                            <button class="image-close-btn" onclick="removeThumbnail(this)"></button>
                        `;
                        document.querySelector('.image-thumbnails').insertBefore(newThumbnail, document.querySelector('.add-thumbnail'));
                        addDragAndDropHandlers(newThumbnail);
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
    draggedElement = event.target;
    event.dataTransfer.effectAllowed = 'move';
    setTimeout(() => { event.target.style.opacity = '0.5'; }, 0);
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

    if (draggedElement && targetElement && targetElement !== draggedElement) {
        const draggedImgSrc = draggedElement.querySelector('img').getAttribute('src');
        const targetImgSrc = targetElement.querySelector('img').getAttribute('src');

        draggedElement.querySelector('img').setAttribute('src', targetImgSrc);
        targetElement.querySelector('img').setAttribute('src', draggedImgSrc);

        const draggedOnClick = draggedElement.querySelector('img').getAttribute('onclick');
        const targetOnClick = targetElement.querySelector('img').getAttribute('onclick');

        draggedElement.querySelector('img').setAttribute('onclick', targetOnClick);
        targetElement.querySelector('img').setAttribute('onclick', draggedOnClick);
    }

    draggedElement.style.opacity = '1';
    draggedElement = null;
}



function handleDragEnd(event) {
    if (draggedElement) {
        draggedElement.style.opacity = '1';
    }
    draggedElement = null;
}



document.querySelectorAll('.thumbnail[draggable="true"]').forEach(function(element) {
    addDragAndDropHandlers(element);
});