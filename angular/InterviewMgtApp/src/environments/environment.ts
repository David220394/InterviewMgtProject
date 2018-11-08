// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  url : "http://localhost:8082",
  outlookId : 'df4664e6-f498-46cd-90df-5b8e629a8d52',
  outlookSecret : 'rqzxeOANXD67*:]sfKP358#',
  response_type : "code",
  scope : "User.Read+Mail.ReadWrite.Shared+Calendars.ReadWrite.Shared+Calendars.Read.Shared+Mail.Send+Mail.Send.Shared"
};

/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
