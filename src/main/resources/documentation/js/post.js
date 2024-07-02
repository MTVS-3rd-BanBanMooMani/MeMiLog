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

function resetIconColor(id) {
    var li = document.getElementById(id);
    li.querySelector('img').setAttribute('src', `../documentation/img/${id}.png`);
    li.setAttribute('onclick', `changeIconColor('${id}')`);
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
            newThumbnail.innerHTML = `
                <img src="${e.target.result}" alt="이미지" onclick="showImageInContainer('${e.target.result}')">
                <button class="image-close-btn" onclick="removeThumbnail(this)"></button>
            `;
            document.querySelector('.image-thumbnails').insertBefore(newThumbnail, document.querySelector('.add-thumbnail'));

            var div = document.getElementById("plus-thumbnail");
            var remove = document.getElementById("addBtn");
            if(div.children.length == 6) {
                div.removeChild(remove);
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

function clearImageContainer() {
    const imageContainer = document.querySelector('.image-container');
    imageContainer.innerHTML = '';
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
                        newThumbnail.innerHTML = `
                            <img src="${e.target.result}" alt="이미지" onclick="showImageInContainer('${e.target.result}')">
                            <button class="image-close-btn" onclick="removeThumbnail(this)"></button>
                        `;
                        document.querySelector('.image-thumbnails').insertBefore(newThumbnail, document.querySelector('.add-thumbnail'));

                        var div = document.getElementById("plus-thumbnail");
                        var remove = document.getElementById("addBtn");
                        if(div.children.length == 6) {
                            div.removeChild(remove);
                        }
                    };
                    reader.readAsDataURL(files[0]);
                }
            });
        }
    }
}
