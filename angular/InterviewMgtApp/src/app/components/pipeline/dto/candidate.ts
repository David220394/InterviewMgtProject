export interface Candidate {
  id : number;
  rating:number;
  name: string;
  address : string;
  picture:string;
  title:string;
  education:string;
  status?:string;
  completeApplication : boolean;
  internalApplication : boolean;
  rehire : boolean;
  skillScore : number;
  skills : string[];

}
