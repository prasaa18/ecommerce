import { Component } from '@angular/core';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent {
  selectedSize : any;
   reviews = [1,1,1,1];

  handleAddToCart(){
     console.log("this selcetd",this.selectedSize)
  }

}
