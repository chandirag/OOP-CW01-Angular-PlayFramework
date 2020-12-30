import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import {RegisteredFootballClub} from "../interfaces/registered-football-club";


@Component({
  selector: 'app-modify-clubs',
  templateUrl: './modify-clubs.component.html',
  styleUrls: ['./modify-clubs.component.css']
})
export class ModifyClubsComponent implements OnInit {
  addClubForm: FormGroup;
  deleteClubForm: FormGroup;
  clubsList: RegisteredFootballClub[];

  constructor(private appService: AppService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.appService.getClubs().subscribe((data: any) => {
      this.clubsList = data;
    })

    this.addClubForm = this.formBuilder.group({
      clubName: ['', [Validators.required]],
      clubLocation: ['', [Validators.required]],
      headCoach: ['', [Validators.required]],
      dayFounded: ['', [Validators.required, Validators.min(1), Validators.max(31)]],
      monthFounded: ['', [Validators.required, Validators.min(1), Validators.max(12)]],
      yearFounded: ['', [Validators.required, Validators.minLength(4)]]
    });

    this.deleteClubForm = this.formBuilder.group({
      existingClubName: ['', [Validators.required]]
    })
  }

  public addNewClub(): void {
    this.appService.createNewClub(
        this.addClubForm.get('clubName').value,
        this.addClubForm.get('clubLocation').value,
        this.addClubForm.get('headCoach').value,
        this.addClubForm.get('dayFounded').value,
        this.addClubForm.get('monthFounded').value,
        this.addClubForm.get('yearFounded').value).subscribe((data: any) => { this.clubsList = data; })
    this.addClubForm.reset();
    this.addClubForm.clearValidators();
  }

  get clubName() { return this.addClubForm.get('clubName'); }
  get clubLocation() { return this.addClubForm.get('clubLocation'); }
  get headCoach() { return this.addClubForm.get('headCoach'); }
  get dayFounded() { return this.addClubForm.get('dayFounded'); }
  get monthFounded() { return this.addClubForm.get('monthFounded'); }
  get yearFounded() { return this.addClubForm.get('yearFounded'); }



  public deleteClub(): void {
    this.appService.deleteExistingClub(this.deleteClubForm.get('existingClubName').value).subscribe((data: any) => {
      this.clubsList = data;
    })
    this.deleteClubForm.reset();
    this.deleteClubForm.clearValidators();
  }

  get existingClubName() { return this.deleteClubForm.get('existingClubName'); }

  public getClubs(): RegisteredFootballClub[] {
    return this.clubsList;
  }

}
