import { Component } from '@angular/core';
import { ApplicationTruthService } from './app.truth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ApplicationTruthService]
})
export class AppComponent {

    title = 'app';
    heroes: Array<any>;

    constructor(private applicationTruthService: ApplicationTruthService) {
        this.heroes = applicationTruthService.getHeroes();
    }
}
