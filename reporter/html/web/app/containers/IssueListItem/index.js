/**
 * IssueListItem
 *
 * Lists the name and the issue count of a repository
 */

import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { createStructuredSelector } from 'reselect';

import ListItem from 'components/ListItem';

export class IssueListItem extends React.PureComponent {
  render() {
    const { item } = this.props;

    // Put together the content of the repository
    const content = (
        <p>{item.title}</p>
    );

    // Render the content into a list item
    return <ListItem key={`repo-list-item-${item.id}`} item={content} />;
  }
}

IssueListItem.propTypes = {
  item: PropTypes.object,
  currentUser: PropTypes.string,
};

export default connect(
  createStructuredSelector({}),
)(IssueListItem);
