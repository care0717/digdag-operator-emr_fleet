1.0.0 (2021-06-21)
==================

* [Change] Change project group
* [Change] Change default wait pooling interval to 30s
* [New Feature] Add step_concurrency_level option
* [New Feature] Add allocation_strategy option to spot_spec
* [Enhancement] Update digdag library to 0.9.42
* [Enhancement] Update aws sdk library to 1.11.1034

0.0.5 (2018-09-09)
==================

* [New Feature] Add `content` option for bootstrap action that uploads content to `script` location.
* [New Feature] Add `run_if` option for bootstrap action that wraps `run-if` script provided by EMR for easier use.

0.0.4 (2018-08-09)
==================

* [Enhancement] Fail correctly if not retryable.

0.0.3 (2018-08-06)
==================

* [Enhancement] Enable to merge emr_fleet.${task_name} exports.

0.0.2 (2018-08-06)
==================

* [Destructive Change] Change `bootstrap_action` configuration specification.
* [Enhancement] Use `RetryExecutor` of digdag-plugin-utils instead of original one.


0.0.1 (2018-07-31)
==================

* First Release
