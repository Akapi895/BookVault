document.addEventListener("DOMContentLoaded", () => {
    const addBookIcon = document.getElementById("addBookIcon");
    const addBookModal = document.getElementById("addBookModal");
    const closeModal = document.getElementById("closeModal");
    const addBookForm = document.getElementById("addBookForm");

    if (!addBookIcon || !addBookModal || !closeModal || !addBookForm) {
        console.error("One or more elements not found. Check IDs in HTML.");
        return;
    }

    // console.log("Elements loaded:", addBookIcon, addBookModal, closeModal);

    // Xử lý sự kiện mở modal
    addBookIcon.addEventListener("click", (e) => {
        e.preventDefault();
        addBookModal.style.display = 'flex';
        console.log("Add-book clicked");
    });

    // Xử lý sự kiện đóng modal
    closeModal.addEventListener("click", () => {
        console.log("Close modal clicked");
        addBookModal.style.display = "none";
    });

    // Xử lý form submit
    addBookForm.addEventListener("submit", (e) => {
        e.preventDefault();

        // Lấy dữ liệu từ form
        const bookId = document.getElementById("bookId").value.trim();
        const bookTitle = document.getElementById("bookTitle").value.trim();
        const bookGenres = document.getElementById("bookGenres").value.trim();

        if (!bookId || !bookTitle || !bookGenres) {
            alert("Please fill out all required fields.");
            return;
        }

        console.log("Form data:", { bookId, bookTitle, bookGenres });

        // Giả lập thêm sách thành công
        alert("Book added successfully!");

        // Reset form và đóng modal
        e.target.reset();
        addBookModal.style.display = "none";
    });
});
