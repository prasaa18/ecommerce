import { Component } from '@angular/core';
import { HostListener } from '@angular/core';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  curentSection:any
  isNavbarContentOpen:any
  
  openNavbarContent(section:any){

          this.isNavbarContentOpen=true;
          this.curentSection=section;
  }

  closeNavbarContent(){
    this.isNavbarContentOpen=false;
  }

  navigateToCart(path:any){

  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
  
      const modalContainer = document.querySelector(".modal-container");
      const openButtons = document.querySelectorAll(".open-button");
      let clickInsideButton = false;
  
      // Convert NodeList to array and iterate over each button
      openButtons.forEach((button: Element) => {
          if (button.contains(event.target as Node)) {
              clickInsideButton = true;
          }
      });
  
      if (modalContainer && !clickInsideButton && this.isNavbarContentOpen) {
          this.closeNavbarContent();
      }
  }
  
}





