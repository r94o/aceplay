<script>
  import { createEventDispatcher } from 'svelte';
  export let backend;

  const dispatch = createEventDispatcher();

  let files;
  let fileField;
  let title = '';
  let artist = '';
  let state = 'READY';

  async function submitTrack(event) {
    state = "SUBMITTING";
    await backend.submitTrack(title, artist);

    title = '';
    artist = '';
    fileField.value = '';
    state = "READY";
  }
</script>

<div class="track">
  <div class="track-name">
    <label for="new-track-title">Title</label>
    <input id="new-track-title" bind:value={title} bind:this={fileField} disabled={state == "SUBMITTING"}>
  </div>
  <div class="track-artist">
    <label for="new-track-artist">Artist</label>
    <input id="new-track-artist" bind:value={artist} disabled={state == "SUBMITTING"}>
  </div>
  <div class="track-ctrl">
    <button on:click={submitTrack} disabled={state == "SUBMITTING"}>Add</button>
  </div>
</div>

<style>
  .track {
    display: grid;
    grid-template-columns: 1fr 1fr 3fr 1fr;
    grid-gap: 10px;
    margin-bottom: 10px;
  }

  input {
    width: 100%;
  }

  .track-ctrl {
    vertical-align: middle;
    margin-top: 1.2em;
  }
</style>
