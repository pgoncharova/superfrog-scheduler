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


 // Sorting function for table columns
function sortTable(columnIndex) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("studentsTable");
  switching = true;
  // Set the sorting direction to ascending:
  dir = "asc";
  // Make a loop that will continue until no switching has been done:
  while (switching) {
    // Start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    // Loop through all table rows (except the first, which contains table headers):
    for (i = 1; i < (rows.length - 1); i++) {
      // Start by saying there should be no switching:
      shouldSwitch = false;
      // Get the two elements you want to compare, one from current row and one from the next:
      x = rows[i].getElementsByTagName("TD")[columnIndex];
      y = rows[i + 1].getElementsByTagName("TD")[columnIndex];
      // Check if the two rows should switch place, based on the direction, asc or desc:
      if (dir == "asc") {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          // If so, mark as a switch and break the loop:
          shouldSwitch= true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      // If a switch has been marked, make the switch and mark that a switch has been done:
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      // Each time a switch is done, increase this count by 1:
      switchcount ++;
    } else {
      // If no switching has been done AND the direction is "asc",
      // set the direction to "desc" and run the while loop again.
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}

// Filter function for table
function filterTable() {
  let input = document.getElementById("student-name-search");
  let filter = input.value.toUpperCase();
  let table = document.getElementById("studentsTable");
  let tr = table.getElementsByTagName("tr");

  for (let i = 0; i < tr.length; i++) {
    let td = tr[i].getElementsByTagName("td")[1]; // Assuming 1 is the index for First Name
    if (td) {
      let txtValue = td.textContent || td.innerText;
      tr[i].style.display = txtValue.toUpperCase().indexOf(filter) > -1 ? "" : "none";
    }
  }
}

document.getElementById("student-name-search").addEventListener("keyup", filterTable);


document.getElementById("student-name-search").addEventListener("keyup", filterTable);

 
 
 