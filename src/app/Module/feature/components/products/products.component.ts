import { Component } from '@angular/core';
import { filters, singleFilter } from './filterData';
import { mensPantsPage1 } from 'src/Data/pants/men_page1';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent {

 filterData:any
 singleFilterData:any
 menPants:any


 constructor(private router:Router ,private activatedRoute:ActivatedRoute){

 }

 ngOnInit(){
  this.filterData=filters;
  this.singleFilterData=singleFilter;
  this.menPants=mensPantsPage1;
 }

 handleMultipleSelectFilter(value: string, sectionId: any) {
  
  const queryParams = { ...this.activatedRoute.snapshot.queryParams }; // Corrected 'activedRoute' to 'activatedRoute'

  const filterValues = queryParams[sectionId] ? queryParams[sectionId].split(",") : [];

  const valueIndex = filterValues.indexOf(value);

  if (valueIndex !== -1) {
    filterValues.splice(valueIndex, 1); //  if present rempove'ele' to 'element  1 '
  } else {
    filterValues.push(value);      // if not present  add it 
  }

  if (filterValues.length > 0) {
    queryParams[sectionId] = filterValues.join(",");
  } else {
    delete queryParams[sectionId];
  }

  this.router.navigate([], { queryParams }); //  'querParms' to 'queryParams'
}


handleSingleFilter(value:String, sectionId:any){
  const queryParams = { ...this.activatedRoute.snapshot.queryParams };
  queryParams[sectionId]=value;
  this.router.navigate([], { queryParams });
}

}
