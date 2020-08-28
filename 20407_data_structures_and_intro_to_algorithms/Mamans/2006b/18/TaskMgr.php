<?php
require_once 'IdAvl.php';
require_once 'PriAvl.php';

class TaskMgr
{
    public $idAvl;
    public $priAvl;

    /**
     * Constructs a new TaskMgr object.
     *
     * Runtime: O(1) (creates of two empty AVL trees).
     */
    public function __construct()
    {
        $this->idAvl = new IdAvl;
        $this->priAvl = new PriAvl;
    }

    /**
     * Inserts a new task.
     *
     * Runtime: O(lg n) (fixed number of AVL operations).
     *
     * @param   integer $id Task id
     * @param   integer $p  Task priority
     * @return  integer     id on success, -1 if already exists
     */
    public function insert($id, $p)
    {
        // return if already exists
        if (!is_null($this->idAvl->search($id))) {
            return -1;
        }

        // creates nodes
        $in = new IdNode($id);
        $pn = new PriNode($p, $id);

        // set refs
        $in->ref = $pn;
        $pn->ref = $in;

        // insert
        $this->idAvl->insert($in);
        $this->priAvl->insert($pn);

        return $id;
    }

    /**
     * Changes a task's priority.
     *
     * Runtime: O(lg n) (fixed number of AVL operations).
     *
     * @param   integer $id Task id
     * @param   integer $p  Task priority
     * @return  integer     id on success, -1 if not found
     */
    public function changePri($id, $p)
    {
        // search task
        $in = $this->idAvl->search($id);

        // not found
        if (is_null($in)) {
            return -1;
        }

        // delete old node from priAvl
        $this->priAvl->delete($in->ref);

        // insert new node to priAvl
        $pn = new PriNode($p, $id);
        $pn->ref = $in;
        $this->priAvl->insert($pn);

        // update inAvl ref
        $in->ref = $pn;

        return $id;
    }

    /**
     * Deletes a task.
     *
     * Runtime: O(lg n) (fixed number of AVL operations).
     *
     * @param   integer $id Task id
     * @return  integer     id on success, -1 if not found
     */
    public function delete($id)
    {
        // search task
        $in = $this->idAvl->search($id);

        // not found
        if (is_null($in)) {
            return -1;
        }

        $this->priAvl->delete($in->ref);
        $this->idAvl->delete($in);

        return $id;
    }

    /**
     * Returns the next task id
     *
     *  Runtime: O(1) (return from reference)
     *
     * @return  integer     id, or -1 if no tasks
     */
    public function next()
    {
        if ($this->priAvl->max) {
            return $this->priAvl->max->id;
        }

        return -1;
    }

}
