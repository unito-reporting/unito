/*
 * App Actions
 *
 * Actions change things in your application
 * Since this boilerplate uses a uni-directional data flow, specifically redux,
 * we have these actions which are the only way your application interacts with
 * your application state. This guarantees that your state is up to date and nobody
 * messes it up weirdly somewhere.
 *
 * To add a new Action:
 * 1) Import your constant
 * 2) Add a function like this:
 *    export function yourAction(var) {
 *        return { type: YOUR_ACTION_CONSTANT, var: var }
 *    }
 */

import { LOAD_ISSUES, LOAD_ISSUES_SUCCESS, LOAD_ISSUES_ERROR } from './constants';

export function loadIssues() {
  return {
    type: LOAD_ISSUES,
  };
}

export function issuesLoaded(issues) {
  return {
    type: LOAD_ISSUES_SUCCESS,
    issues: issues
  };
}

export function issuesLoadingError(error) {
  return {
    type: LOAD_ISSUES_ERROR,
    error,
  };
}
