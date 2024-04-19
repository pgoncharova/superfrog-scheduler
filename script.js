document.addEventListener('DOMContentLoaded', function() {
  // Dropdown functionality
  var dropdownHeaders = document.querySelectorAll('.sidebar-section-header');
  dropdownHeaders.forEach(function(header) {
    header.addEventListener('click', function(event) {
      // Prevent redirection if it's just a "#" link.
      event.preventDefault();
      
      // Toggle the visibility of the dropdown links.
      var dropdownLinks = this.nextElementSibling;
      var isOpen = dropdownLinks.style.display === 'block';
      
      // Close all dropdowns.
      document.querySelectorAll('.sidebar-section-links').forEach(function(links) {
        links.style.display = 'none';
      });
      
      // Remove 'active' from all headers.
      dropdownHeaders.forEach(function(otherHeader) {
        otherHeader.classList.remove('active');
      });

      // If the dropdown was not already open, open it and add 'active' class.
      if (!isOpen) {
        dropdownLinks.style.display = 'block';
        this.classList.add('active');
      }
    });
  });

  // Highlight the active link and open the dropdown if necessary.
  var currentPage = window.location.pathname.split('/').pop();
  var activeLink = document.querySelector('.sidebar a[href="' + currentPage + '"]');
  if (activeLink) {
    activeLink.classList.add('active');
    var parentDropdown = activeLink.closest('.sidebar-section-links');
    if (parentDropdown) {
      parentDropdown.style.display = 'block';
      var dropdownHeader = parentDropdown.previousElementSibling;
      dropdownHeader.classList.add('active');
    }
  }
});
