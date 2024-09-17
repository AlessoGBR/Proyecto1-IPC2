function previewImage(event) {
    const input = event.target;
    const file = input.files[0];
    const previewContainer = document.getElementById('imagePreviewContainer');
    const preview = document.getElementById('imagePreview');

    if (file && file.type.startsWith('image/')) {
        const reader = new FileReader();
        reader.onload = function (e) {
            preview.src = e.target.result;
            previewContainer.style.display = 'flex';
        };
        reader.readAsDataURL(file);
    } else {
        previewContainer.style.display = 'none';
        preview.src = '';
    }
}

document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.category-tag').forEach(tag => {
        tag.addEventListener('click', function () {
            toggleTagSelection(this);
        });
    });

    function toggleTagSelection(tagElement) {
        const selectedTagsContainer = document.getElementById('selectedTags');
        const tagText = tagElement.textContent.trim();
        const tagAlreadySelected = tagElement.classList.contains('selected');

        if (tagAlreadySelected) {
            tagElement.classList.remove('selected');
            Array.from(selectedTagsContainer.children).forEach(child => {
                if (child.textContent.trim() === tagText) {
                    selectedTagsContainer.removeChild(child);
                }
            });
        } else {
            tagElement.classList.add('selected');
            const selectedTag = tagElement.cloneNode(true);
            selectedTagsContainer.appendChild(selectedTag);

            selectedTag.addEventListener('click', function () {
                toggleTagSelection(tagElement);
            });
        }
    }

    document.getElementById('enviar').addEventListener('click', function () {
        const selectedTagsContainer = document.getElementById('selectedTags');
        const selectedTags = Array.from(selectedTagsContainer.children)
                .map(tag => tag.textContent.trim());
        document.getElementById('selectedTagsInput').value = selectedTags.join(',');

        localStorage.setItem('selectedTags', JSON.stringify(selectedTags));
    });
});
function validateForm() {
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirmPassword');
    const passwordHelp = document.getElementById('passwordHelp');
    const confirmPasswordHelp = document.getElementById('confirmPasswordHelp');

    if (password.value !== confirmPassword.value) {
        password.classList.add('is-invalid');
        confirmPassword.classList.add('is-invalid');
        confirmPasswordHelp.classList.remove('d-none');
        confirmPasswordHelp.classList.add('d-block');
        return false;
    } else {
        password.classList.remove('is-invalid');
        confirmPassword.classList.remove('is-invalid');
        confirmPasswordHelp.classList.add('d-none');
        confirmPasswordHelp.classList.remove('d-block');
        passwordHelp.classList.remove('d-none');
        passwordHelp.classList.add('d-block');
        return true;
    }
}

function selectCategory(event) {
    const category = event.target;
    const selectedCategories = document.getElementById('selectedCategories');
    if (category.tagName === 'OPTION') {
        category.selected = !category.selected;
        if (category.selected) {
            selectedCategories.value += `${category.text}, `;
        } else {
            selectedCategories.value = selectedCategories.value.replace(`${category.text}, `, '');
        }
    }
}

document.addEventListener('DOMContentLoaded', function () {
    var errorDiv = document.querySelector('.alert-danger');
    if (errorDiv && !errorDiv.textContent.trim()) {
        errorDiv.style.display = 'none';
    }
});
document.addEventListener("DOMContentLoaded", function () {
    var elements = document.getElementsByTagName("INPUT");
    for (var i = 0; i < elements.length; i++) {
        elements[i].oninvalid = function (e) {
            e.target.setCustomValidity("");
            if (!e.target.validity.valid) {
                e.target.setCustomValidity("Por favor llenar el campo");
            }
        };
        elements[i].oninput = function (e) {
            e.target.setCustomValidity("");
        };
    }
});