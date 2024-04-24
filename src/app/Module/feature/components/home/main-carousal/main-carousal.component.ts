import { Component, OnInit, OnDestroy } from '@angular/core';
import { homeCarouselData } from 'src/Data/mainCarousel';

@Component({
  selector: 'app-main-carousal',
  templateUrl: './main-carousal.component.html',
  styleUrls: ['./main-carousal.component.scss']
})
export class MainCarousalComponent {
 
 carouselData:any;
  currentSlide=0;
  interval: any;

  ngOnInit() {
     this.carouselData=homeCarouselData
    this.autoPlay();
  }

  autoPlay() {
    setInterval(() => {
      this.nextSlide();
    }, 2000); 
  }

  nextSlide() {
    this.currentSlide = (this.currentSlide + 1) % this.carouselData.length;
    console.log("current slide - ",this.currentSlide)
  }

  prevSlide() {
    this.currentSlide = (this.currentSlide - 1 + this.carouselData.length) % this.carouselData.length;
  }
}

