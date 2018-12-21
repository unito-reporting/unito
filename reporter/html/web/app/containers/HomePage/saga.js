/**
 * Gets the issues of the user from Github
 */

import { call, put, takeLatest } from 'redux-saga/effects';
import { LOAD_ISSUES } from 'containers/App/constants';
import { issuesLoaded, issuesLoadingError } from 'containers/App/actions';

import request from 'utils/request';

/**
 * Github issues request/response handler
 */
export function* getUnitoData() {
  // Select issueToHide from store
  const requestURL = `https://gist.githubusercontent.com/lukaville/67b6c5ed55a1eaf1051f6e09522e7a3e/raw/7106936057d762f441e087e0b2176332984099b9/issues.json`;

  try {
    // Call our request helper (see 'utils/request')
    const issues = yield call(request, requestURL);
    yield put(issuesLoaded(issues.issues));
  } catch (err) {
    yield put(issuesLoadingError(err));
  }
}

/**
 * Root saga manages watcher lifecycle
 */
export default function* unitoData() {
  // Watches for LOAD_ISSUES actions and calls getUnitoData when one comes in.
  // By using `takeLatest` only the result of the latest API call is applied.
  // It returns task descriptor (just like fork) so we can continue execution
  // It will be cancelled automatically on component unmount
  yield takeLatest(LOAD_ISSUES, getUnitoData);
}
