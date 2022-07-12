<script>
  import { createEventDispatcher } from 'svelte';
  const dispatch = createEventDispatcher();

  function deleteTrack(track) {
    dispatch('deleteTrack', track);
  }

  export let tracks;
</script>

{#each tracks as track}
  <div class="track">
    <div class="track-name">
      {track.title}
    </div>
    <div class="track-artist">
      {track.artist}
    </div>
    <div class="track-ctrl">
      <button on:click={() => deleteTrack(track)}>Delete</button>
    </div>
  </div>
{/each}

<style>
  .track {
    display: grid;
    grid-template-columns: 1fr 1fr 3fr 1fr;
    grid-gap: 10px;
    margin-bottom: 10px;
  }
</style>
