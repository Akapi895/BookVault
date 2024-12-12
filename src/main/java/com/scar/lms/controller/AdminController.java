package com.scar.lms.controller;

import com.scar.lms.entity.Role;
import com.scar.lms.entity.User;
import com.scar.lms.model.NotificationDTO;
import com.scar.lms.service.AuthenticationService;
import com.scar.lms.service.BookService;
import com.scar.lms.service.BorrowService;
import com.scar.lms.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static com.scar.lms.entity.Role.ADMIN;

@SuppressWarnings("SameReturnValue")
@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final BookService bookService;
    private final BorrowService borrowService;

    public AdminController(final UserService userService,
                           final AuthenticationService authenticationService,
                           final BookService bookService, BorrowService borrowService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.bookService = bookService;
        this.borrowService = borrowService;
    }

    @GetMapping("/users")
    public CompletableFuture<String> listAllUsers(Model model) {
        return userService.findAllUsers()
                .thenApply(users -> {
                    if (users == null) {
                        model.addAttribute("error", "Users not found.");
                        return "error/404";
                    } else {
                        model.addAttribute("users", users);
                        return "total-user";
                    }
                })
                .exceptionally(e -> {
                    log.error("Failed to fetch users.", e);
                    model.addAttribute("error", "Failed to fetch users.");
                    return "error/404";
                });
    }

//    @GetMapping("/books")
//    public CompletableFuture<String> listAllBooks(Model model) {
//        return bookService.findAllBooks()
//                .thenApply(books -> {
//                    if (books == null) {
//                        model.addAttribute("error", "Books not found.");
//                        return "error/404";
//                    } else {
//                        model.addAttribute("books", books);
//                        return "book-list";
//                    }
//                })
//                .exceptionally(e -> {
//                    log.error("Failed to fetch books.", e);
//                    model.addAttribute("error", "Failed to fetch books.");
//                    return "error/404";
//                });
//    }

    @GetMapping("/total-book")
    public CompletableFuture<String> listAllBooks(Model model) {
        return bookService.findAllBooks()
                .thenApply(books -> {
                    if (books == null) {
                        model.addAttribute("error", "Books not found.");
                        return "error/404";
                    }
                    model.addAttribute("books", books);
                    return "total-book";
                })
                .exceptionally(e -> {
                    log.error("Failed to fetch books.", e);
                    model.addAttribute("error", "Failed to fetch books.");
                    return "error/404";
                });
    }

    @GetMapping("/total-borrow")
    public CompletableFuture<String> listAllBorrows(Model model) {
        return borrowService.findAllBorrows()
                .thenApply(borrows -> {
                    if (borrows == null) {
                        model.addAttribute("error", "Borrows not found.");
                        return "error/404";
                    }
                    model.addAttribute("borrows", borrows);
                    return "total-borrow";
                })
                .exceptionally(e -> {
                    log.error("Failed to fetch borrows.", e);
                    model.addAttribute("error", "Failed to fetch borrows.");
                    return "error/404";
                });
    }

    @GetMapping("/user/search")
    public CompletableFuture<String> searchUsers(@RequestParam String keyword, Model model) {
        return userService.searchUsers(keyword)
                .thenApply(users -> {
                    if (users.isEmpty()) {
                        model.addAttribute("message", "No users found.");
                    } else {
                        model.addAttribute("users", users);
                    }
                    return "user-list";
                })
                .exceptionally(e -> {
                    log.error("Failed to search users.", e);
                    model.addAttribute("error", "Failed to search users.");
                    return "error/404";
                });
    }

//    @PostMapping("/user/update")
//    public CompletableFuture<ResponseEntity<String>> updateUser(
//            @Valid @ModelAttribute("user") User user,
//            BindingResult result) {
//        if (result.hasErrors()) {
//            return CompletableFuture.completedFuture(
//                    ResponseEntity.badRequest().body("Validation failed: " + result.getFieldErrors())
//            );
//        }
//
//        return userService.findUserById(user.getId())
//                .thenApply(existingUser -> {
//                    if (existingUser != null) {
//                        existingUser.setDisplayName(user.getDisplayName());
//                        existingUser.setEmail(user.getEmail());
//
//                        try {
//                            existingUser.setRole(user.getRole());
//                        } catch (IllegalArgumentException e) {
//                            return ResponseEntity.badRequest().body("Invalid role: " + user.getRole());
//                        }
//
//                        userService.updateUser(existingUser);
//
//                        return ResponseEntity.ok("User updated successfully");
//                    } else {
//                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//                    }
//                })
//                .exceptionally(e -> {
//                    log.error("Failed to update user.", e);
//                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                            .body("Failed to update user due to an error");
//                });
//    }

    @GetMapping("/admin-profile")
    public CompletableFuture<String> showAdminProfilePage(Model model, Authentication authentication) {
        if (authentication == null) {
            return CompletableFuture.completedFuture("redirect:/login");
        }

        return authenticationService.getAuthenticatedUser(authentication)
                .thenApply(admin -> {
                    if (admin == null) {
                        model.addAttribute("error", "Admin not found or unauthorized.");
                        return "error/404";
                    }

                    model.addAttribute("admin", admin);
                    model.addAttribute("userCount", userService.countAllUsers().join());
                    model.addAttribute("bookCount", bookService.countAllBooks().join());
                    return "admin-profile";
                });
    }

    @GetMapping("/")

    @PostMapping("/user/update")
    public CompletableFuture<ResponseEntity<String>> updateUser(
            @RequestParam("id") Integer id,
            @RequestParam("username") String username,
            @RequestParam("displayName") String displayName,
            @RequestParam("role") String role) {

        return userService.findUserById(id)
                .thenApply(existingUser -> {
                    if (existingUser != null) {
                        // Cập nhật dữ liệu người dùng
                        existingUser.setUsername(username);
                        existingUser.setDisplayName(displayName);
                        try {
                            existingUser.setRole(Role.valueOf(role.toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            return ResponseEntity.badRequest().body("Invalid role: " + role);
                        }

                        userService.updateUser(existingUser);
                        return ResponseEntity.ok("User updated successfully");
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
                    }
                })
                .exceptionally(e -> {
                    log.error("Failed to update user.", e);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Failed to update user due to an error");
                });
    }


    @PostMapping("/user/delete/{userId}")
    public String deleteUser(@PathVariable int userId, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(userId);
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete user.");
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/remove/{bookId}")
    public String deleteBook(@PathVariable int bookId, RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteBook(bookId);
            redirectAttributes.addFlashAttribute("successMessage", "Book deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete book.");
        }
        return "redirect:/admin/total-book";
    }

    @PostMapping("/borrow-remove/{borrowId}")
    public String deleteBorrow(@PathVariable int borrowId, RedirectAttributes redirectAttributes) {
        try {
            borrowService.removeBorrow(borrowId);
            redirectAttributes.addFlashAttribute("successMessage", "Book deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete book.");
        }
        return "redirect:/admin/total-borrow";
    }

    @PostMapping("/user/new")
    public String createUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "total-user";
        }
        userService.createUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/user/grantAuthority/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public CompletableFuture<String> grantAuthority(@PathVariable int userId, RedirectAttributes redirectAttributes) {
        return userService.findUserById(userId)
                .thenApply(user -> {
                    if (user == null) {
                        redirectAttributes.addFlashAttribute("errorMessage", "User not found.");
                    } else {
                        user.setRole(ADMIN);
                        userService.updateUser(user);
                        redirectAttributes.addFlashAttribute("successMessage", "Authority granted successfully.");
                    }
                    return "redirect:/admin/users";
                })
                .exceptionally(_ -> {
                    redirectAttributes.addFlashAttribute("errorMessage", "Failed to grant authority.");
                    return "redirect:/admin/users";
                });
    }

    @GetMapping({"", "/"})
    public CompletableFuture<String> showAdminProfile(Model model, Authentication authentication) {
        if (authentication == null) {
            return CompletableFuture.completedFuture("redirect:/login");
        }

        return authenticationService.getAuthenticatedUser(authentication)
                .thenCompose(user -> {
                    if (user == null) {
                        model.addAttribute("error", "User not found.");
                        return CompletableFuture.completedFuture("error/404");
                    }

                    model.addAttribute("admin", user);

                    CompletableFuture<Long> adminCountFuture = userService.countUsersByRole(ADMIN);
                    CompletableFuture<Long> userCountFuture = userService.countAllUsers();
                    CompletableFuture<Long> bookCountFuture = bookService.countAllBooks();
                    CompletableFuture<Long> borrowCountFuture = borrowService.countAllBorrows();

                    List<CompletableFuture<Long>> borrowCountMonthFutures = IntStream.rangeClosed(1, 12)
                            .mapToObj(borrowService::countBorrowsByMonth)
                            .toList();

                    List<CompletableFuture<?>> futures = new ArrayList<>();
                    futures.add(adminCountFuture);
                    futures.add(userCountFuture);
                    futures.add(bookCountFuture);
                    futures.add(borrowCountFuture);
                    futures.addAll(borrowCountMonthFutures);

                    CompletableFuture<Void> allOfFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

                    return allOfFutures.thenApply(_ -> {
                        try {
                            model.addAttribute("adminCount", adminCountFuture.get());
                            model.addAttribute("userCount", userCountFuture.get());
                            model.addAttribute("bookCount", bookCountFuture.get());
                            model.addAttribute("borrowCount", borrowCountFuture.get());

                            for (int i = 1; i <= 12; i++) {
                                model.addAttribute("borrowCountMonth" + i, borrowCountMonthFutures.get(i - 1).get());
                            }
                        } catch (Exception e) {
                            log.error("Error fetching admin profile data", e);
                            model.addAttribute("error", "Failed to load admin profile data.");
                            return "error/500";
                        }
                        return "admin";
                    });
                });
    }

    @PostMapping("/profile/edit")
    public CompletableFuture<String> showEditAdminProfileForm(Authentication authentication, Model model) {
        return authenticationService.getAuthenticatedUser(authentication)
                .thenApply(user -> {
                    model.addAttribute("user", user);
                    return "admin-profile";
                });
    }

    @GetMapping("/notifications")
    public String showNotificationPage() {
        return "notifications";
    }

    @MessageMapping("/notificationDTO.sendNotification")
    @SendTo("/topic/notifications")
    public NotificationDTO sendNotification(@Payload NotificationDTO notificationDTO) {
        return notificationDTO;
    }
}
