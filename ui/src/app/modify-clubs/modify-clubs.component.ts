import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { FormBuilder, FormGroup, FormGroupDirective, Validators } from "@angular/forms";
import { RegisteredFootballClub } from "../interfaces/registered-football-club";
import { MatDialog } from "@angular/material/dialog";
import { DialogDeletedComponent } from "../dialog-components/dialog-deleted/dialog-deleted.component";
import { DialogAddedComponent } from "../dialog-components/dialog-added/dialog-added.component";


@Component({
  selector: 'app-modify-clubs',
  templateUrl: './modify-clubs.component.html',
  styleUrls: ['./modify-clubs.component.css']
})
export class ModifyClubsComponent implements OnInit {
  addClubForm: FormGroup;
  deleteClubForm: FormGroup;
  clubsList: RegisteredFootballClub[];

  constructor(private appService: AppService, private formBuilder: FormBuilder, public dialog: MatDialog) { }

  ngOnInit() {
    // Makes a http GET request to get the currently registered club details as a JSON response
    // Assigns the JSON response (which is of type RegisteredFootballClub) to the array
    this.appService.getClubs().subscribe((data: any) => {
      this.clubsList = data;
    })

    // Initializing Reactive form to add club
    this.addClubForm = this.formBuilder.group({
      clubName: ['', [Validators.required]],
      clubLocation: ['', [Validators.required]],
      headCoach: ['', [Validators.required]],
      dayFounded: ['', [Validators.required, Validators.min(1), Validators.max(31), Validators.pattern("^[0-9]*$")]],
      monthFounded: ['', [Validators.required, Validators.min(1), Validators.max(12), Validators.pattern("^[0-9]*$")]],
      yearFounded: ['', [Validators.required, Validators.minLength(4), Validators.pattern("^[0-9]*$")]]
    });

    // Initializing Reactive form to delete club
    this.deleteClubForm = this.formBuilder.group({
      existingClubName: ['', [Validators.required]]
    })
  }


  public addNewClub(): void {
    this.showClubAddedDialog();
    this.appService.createNewClub(
        this.addClubForm.get('clubName').value,
        this.addClubForm.get('clubLocation').value,
        this.addClubForm.get('headCoach').value,
        this.addClubForm.get('dayFounded').value,
        this.addClubForm.get('monthFounded').value,
        this.addClubForm.get('yearFounded').value).subscribe((data: any) => { this.clubsList = data; })
    this.addClubForm.reset();
    this.addClubForm.clearValidators();
    // alert("Club added.")
  }

  get clubName() { return this.addClubForm.get('clubName'); }
  get clubLocation() { return this.addClubForm.get('clubLocation'); }
  get headCoach() { return this.addClubForm.get('headCoach'); }
  get dayFounded() { return this.addClubForm.get('dayFounded'); }
  get monthFounded() { return this.addClubForm.get('monthFounded'); }
  get yearFounded() { return this.addClubForm.get('yearFounded'); }

  // Show alert box asking user to confirm deletion of a club
  public showConfirmDeleteDialog(formDirective: FormGroupDirective) {
    let confirmDeleteDialogRef = this.dialog.open(DialogDeletedComponent, { width: '400px'});
    confirmDeleteDialogRef.afterClosed().subscribe(data => {
      // If user confirms delete
      if (data == 'deleteClub') {
          this.deleteClub(formDirective);
      }
    })
  }

  // Show alert to notify user that a club has been added
  public showClubAddedDialog() {
    this.dialog.open(DialogAddedComponent, {width: '400px'})
  }

  // Method to delete existing club
  public deleteClub(formDirective: FormGroupDirective): void {
    // Makes a http POST request to send data to the backend to delete an existing club
    this.appService.deleteExistingClub(this.deleteClubForm.get('existingClubName').value).subscribe((data: any) => {
      this.clubsList = data;
    })
    formDirective.resetForm();
    this.deleteClubForm.reset();
  }

  get existingClubName() { return this.deleteClubForm.get('existingClubName'); }

  public getClubs(): RegisteredFootballClub[] {
    return this.clubsList;
  }

}
