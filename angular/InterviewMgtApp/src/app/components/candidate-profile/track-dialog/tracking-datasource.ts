import { Tracking } from "../dtos/tracking";
import { DataSource } from "@angular/cdk/table";
import { BehaviorSubject, Observable, of } from "rxjs";
import { TrackingService } from "../providers/tracking/tracking.service";
import { CollectionViewer } from "@angular/cdk/collections";
import { catchError, finalize } from "rxjs/operators";

export class TrackingDataSource implements DataSource<Tracking> {

  private trackings = new BehaviorSubject<Tracking[]>([]);
  private loadingTracking = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingTracking.asObservable();

  constructor(private trackingService: TrackingService) {}

  connect(collectionViewer: CollectionViewer): Observable<Tracking[]> {
    return this.trackings.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.trackings.complete();
    this.loadingTracking.complete();
  }

  loadTracks(jobId : string, candidateId : string) {
    this.loadingTracking.next(true);

    this.trackingService.findTrackingByJobIdAndCandidateId(jobId,candidateId)
                        .pipe(catchError(() => of([])),
                          finalize(() => this.loadingTracking.next(false))
                        ).subscribe(tracks => this.trackings.next(tracks));
  }
}
